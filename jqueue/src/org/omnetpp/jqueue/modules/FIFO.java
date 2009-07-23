package org.omnetpp.jqueue.modules;

import java.util.ArrayList;
import java.util.List;

import org.omnetpp.jqueue.IJob;
import org.omnetpp.jqueue.IResource;
import org.omnetpp.jqueue.IResourceExpression;
import org.omnetpp.jqueue.IServer;
import org.omnetpp.jqueue.IServerPool;
import org.omnetpp.jqueue.ResourceAllocator;
import org.omnetpp.jqueue.ServerPool;
import org.omnetpp.simkernel.JSimpleModule;
import org.omnetpp.simkernel.cMessage;


public class FIFO extends JSimpleModule {

	List<IJob> jobList = new ArrayList<IJob>();
	private IServerPool serverPool = new ServerPool();
	private double serviceTime;
	IJob processedJob;
	
	ResourceAllocator ralloc = new ResourceAllocator() {

		@Override
		protected List<IJob> selectJobs() {
			return jobList;
		}
		
		@Override
		protected void onAllocation(List<IJob> jobs) {
			processedJob = jobList.remove(0);
			assert(jobs.size() == 1 && jobs.get(0) == processedJob);
		}
		
		@Override
		public void serverFinished(IServer server) {
			send((cMessage)processedJob, "out");
		}
		
	};
	
	@Override
	protected void initialize() {
		ralloc.setResourceExpression(new IResourceExpression() {

			@Override
			public List<IResource> tryAllocateResourcesFor(IJob jobToTest) {
				List<IResource> allocated = new ArrayList<IResource>();
				IServer res = serverPool.allocate(serviceTime);
				allocated.add(res);
				return allocated;
			}
			
		});
		// TODO Auto-generated method stub
		super.initialize();
	}
	
	@Override
	protected void handleMessage(cMessage msg) {
		jobList.add((IJob)msg);
		ralloc.tryToAllocateResources();
	}
}
