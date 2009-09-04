package org.omnetpp.jqueue.modules;

import java.util.ArrayList;
import java.util.List;

import org.omnetpp.jqueue.IJob;
import org.omnetpp.jqueue.IResourceExpression;
import org.omnetpp.jqueue.IResourcePool;
import org.omnetpp.jqueue.IServerPool;
import org.omnetpp.jqueue.ResourceAllocatorImpl;
import org.omnetpp.jqueue.ServerPoolImpl;
import org.omnetpp.jqueue.IResourcePool.IResource;
import org.omnetpp.jqueue.IServerPool.IServer;
import org.omnetpp.simkernel.JSimpleModule;
import org.omnetpp.simkernel.cMessage;


public class FIFOwith2CPUPool extends JSimpleModule {

	List<IJob> queue = new ArrayList<IJob>();
	List<IJob> activeJobs = new ArrayList<IJob>();
	private IServerPool server1Pool = new ServerPoolImpl("cpu", this);
	private IServerPool server2Pool = new ServerPoolImpl("cpu", this);
	private double serviceTime;
	
	ResourceAllocatorImpl ralloc = new ResourceAllocatorImpl() {

		@Override
		protected List<IJob> getJobList() {
			return queue;
		}
		
		@Override
		protected void onAllocation(List<IJob> jobs) {
			activeJobs.addAll(jobs);
			queue.retainAll(jobs);
		}
		
		@Override
		public void onServerFinished(IJob job, IServer server) {
			activeJobs.remove(job);
			send((cMessage)job, "out");
		}

		@Override
		protected void onServerInterrupted(IJob job, IServer server) {
			throw new RuntimeException("Interrupt not supported in FIFO");
		}
		
	};
	
	@Override
	protected void initialize() {
		
		ralloc.setResourceExpression(new IResourceExpression() {

			// resource selection strategy
			@Override
			public List<IResource> tryAllocateResourcesFor(IJob jobToTest) {
				List<IResource> allocated = new ArrayList<IResource>();
				IServer res;
				// check which cpu has less utilization
				IServerPool pool;
				if (server1Pool.getAmount() >= 1 && server2Pool.getAmount() >=1)
					pool = (server1Pool.getUtilization() < server2Pool.getUtilization() ? server1Pool : server2Pool);
				else if (server1Pool.getAmount()>=1)
					pool = server1Pool;
				else if (server2Pool.getAmount()>=1)
					pool = server2Pool;
				else
					pool = null;
					
				if (pool != null) {
					res = (IServer)pool.allocate(String.valueOf(serviceTime));
					allocated.add(res);
				}
				return allocated;
			}

			@Override
			public List<IResourcePool> getReferencedResourcePools() {
				List<IResourcePool> pools = new ArrayList<IResourcePool>();
				pools.add(server1Pool);
				pools.add(server2Pool);
				return pools;
			}
		});
		ralloc.hookResourceChangeListeners();

		super.initialize();
	}
	
	@Override
	protected void handleMessage(cMessage msg) {
		queue.add((IJob)msg);
		ralloc.tryToAllocateResources();
	}
}
