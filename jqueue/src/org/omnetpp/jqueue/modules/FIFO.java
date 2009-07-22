package org.omnetpp.jqueue.modules;

import java.util.ArrayList;
import java.util.List;

import org.omnetpp.jqueue.IJob;
import org.omnetpp.jqueue.IServer;
import org.omnetpp.jqueue.ResourceAllocator;
import org.omnetpp.simkernel.JSimpleModule;
import org.omnetpp.simkernel.cMessage;


public class FIFO extends JSimpleModule {

	List<IJob> jobList = new ArrayList<IJob>();
	IJob processedJob;
	
	ResourceAllocator ralloc = new ResourceAllocator() {

		@Override
		protected List<IJob> selectJobs() {
			List<IJob> jobs = new ArrayList<IJob>();
			if (!jobList.isEmpty())
				jobs.add(jobList.get(0));
			return jobs;
		}
		
		@Override
		protected void onAllocation(List<IJob> jobs) {
			processedJob = jobList.remove(0);
			assert(jobs.get(0) == processedJob);
		}
		
		@Override
		public void serverFinished(IServer server) {
			send((cMessage)processedJob, "out");
		}
		
	};
	
	@Override
	protected void handleMessage(cMessage msg) {
		jobList.add((IJob)msg);
		ralloc.tryToAllocateResources();
	}
}
