package org.omnetpp.jqueue.modules;

import java.util.ArrayList;
import java.util.List;

import org.omnetpp.jqueue.FIFOAllocationStrategy;
import org.omnetpp.jqueue.IJob;
import org.omnetpp.jqueue.IResourceExpression;
import org.omnetpp.jqueue.IResourcePool;
import org.omnetpp.jqueue.IServerPool;
import org.omnetpp.jqueue.Job;
import org.omnetpp.jqueue.ResourceAllocatorImpl;
import org.omnetpp.jqueue.ServerPoolImpl;
import org.omnetpp.jqueue.IResourcePool.IResource;
import org.omnetpp.jqueue.IServerPool.IServer;
import org.omnetpp.simkernel.JMessage;
import org.omnetpp.simkernel.JSimpleModule;
import org.omnetpp.simkernel.cMessage;


public class FIFO extends JSimpleModule {

	List<IJob> queue = new ArrayList<IJob>();
	List<IJob> activeJobs = new ArrayList<IJob>();
	private IServerPool serverPool = new ServerPoolImpl("cpu", this);
	private double serviceTime = 1.0;
	
	ResourceAllocatorImpl ralloc = new ResourceAllocatorImpl() {

		@Override
		protected List<IJob> getJobList() {
			return queue;
		}
		
		@Override
		protected void onAllocation(List<IJob> jobs) {
			activeJobs.addAll(jobs);
			queue.removeAll(jobs);
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
				if (serverPool.getAmount() == 0)
					return null;

				List<IResource> allocated = new ArrayList<IResource>();
				IServer res = (IServer)serverPool.allocate(String.valueOf(serviceTime));
				allocated.add(res);
				
				return allocated;
			}

			@Override
			public List<IResourcePool> getReferencedResourcePools() {
				List<IResourcePool> pools = new ArrayList<IResourcePool>();
				pools.add(serverPool);
				return pools;
			}
			
		});
		ralloc.setJobAllocStrat(new FIFOAllocationStrategy());
		ralloc.hookResourceChangeListeners();
		
		super.initialize();
	}
	
	@Override
	protected void handleMessage(cMessage msg) {
		JMessage jmsg = JMessage.cast(msg);
		assert(jmsg != null);
		if ( jmsg instanceof IServer) {
			((IServer)jmsg).getResourcePool().deallocate((IServer)jmsg);
		} else {
			Job job = (Job)jmsg;
			assert(job != null);
			queue.add(job);
			ralloc.tryToAllocateResources();
		}
		getDisplayString().setTagArg("t", 0, "length:"+queue.size());
		getDisplayString().setTagArg("tt", 0, queue.toString());
	}
}
