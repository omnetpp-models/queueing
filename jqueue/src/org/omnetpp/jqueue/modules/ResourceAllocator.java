package org.omnetpp.jqueue.modules;

import java.util.ArrayList;
import java.util.List;

import org.omnetpp.jqueue.FIFOAllocationStrategy;
import org.omnetpp.jqueue.IJob;
import org.omnetpp.jqueue.IResourceChangeListener;
import org.omnetpp.jqueue.IResourceExpression;
import org.omnetpp.jqueue.IResourcePool;
import org.omnetpp.jqueue.IServerListener;
import org.omnetpp.jqueue.Job;
import org.omnetpp.jqueue.ResourceAllocatorImpl;
import org.omnetpp.jqueue.ResourceExpressionImpl;
import org.omnetpp.jqueue.IServerPool.IServer;
import org.omnetpp.simkernel.JMessage;
import org.omnetpp.simkernel.JSimpleModule;
import org.omnetpp.simkernel.cMessage;
import org.omnetpp.simkernel.cMethodCallContextSwitcher;


// TODO support sending out the jobs with active resources immediately after allocation (i.e. do not put 
// it into the activeJobs list. Instead send it out.
// TODO support interrupting the job processing (restart/continue can be configured)
public class ResourceAllocator extends JSimpleModule implements IServerListener, IResourceChangeListener {
	List<IJob> waitingForAllocation = new ArrayList<IJob>();
	List<IJob> waitingForDeallocation = new ArrayList<IJob>();

	ResourceAllocatorImpl resourceAllocImpl = new ResourceAllocatorImpl() {

		@Override
		protected List<IJob> getJobList() {
			return waitingForAllocation;
		}
		
		@Override
		protected void onAllocation(List<IJob> jobs) {
			waitingForDeallocation.addAll(jobs);
			waitingForAllocation.removeAll(jobs);
			updateDisplayString();
		}
		
		@Override
		public void onServerFinished(IJob job, IServer server) {
			waitingForDeallocation.remove(job);
			send((cMessage)job, "out");
			updateDisplayString();
		}

		@Override
		protected void onServerInterrupted(IJob job, IServer server) {
			// TODO release all resources allocated in this module and put back the
			// job to the waiting for allocation queue (or send it out on the exception gate)
			throw new RuntimeException("Interrupt not supported in ResourceAllocator");
		}
		
	};
	
	@Override
	protected void initialize() {
		
		List<IResourcePool> pools = new ArrayList<IResourcePool>();
		int size = gateSize("usesResource");
		for (int i=0; i<size; ++i) {
			JSimpleModule mod = JSimpleModule.cast(gate("usesResource",i).getPathStartGate().getOwnerModule());
			if (mod instanceof IResourcePool)
				pools.add((IResourcePool)mod);
			else
				error("Using a non-resource module as a resource pool ("+mod.getClassName()+")");
		}
		IResourceExpression rexpr = new ResourceExpressionImpl(par("allocationExpression").str(), pools);
		resourceAllocImpl.setResourceExpression(rexpr);
		resourceAllocImpl.setJobAllocStrat(new FIFOAllocationStrategy());
		resourceAllocImpl.hookResourceChangeListeners();
		
		super.initialize();
	}
	
	@Override
	protected void handleMessage(cMessage msg) {
		JMessage jmsg = JMessage.cast(msg);
		assert(jmsg != null);
		Job job = (Job)jmsg;
		waitingForAllocation.add(job);
		resourceAllocImpl.tryToAllocateResources();
		
		updateDisplayString();
	}

	public void resourceChanged(IResourcePool pool) {
		cMethodCallContextSwitcher methodCallContextSwitcher = new cMethodCallContextSwitcher(this);
		methodCallContextSwitcher.methodCall("resourceChanged");
		try {
			resourceAllocImpl.resourceChanged(pool);
		} finally {
			methodCallContextSwitcher.delete();
		}
	}

	public void serverFinished(IServer server) {
		cMethodCallContextSwitcher methodCallContextSwitcher = new cMethodCallContextSwitcher(this);
		methodCallContextSwitcher.methodCall("serverFinished");
		try {
			resourceAllocImpl.serverFinished(server);
		} finally {
			methodCallContextSwitcher.delete();
		}
	}

	public void serverInterrupted(IServer server) {
		cMethodCallContextSwitcher methodCallContextSwitcher = new cMethodCallContextSwitcher(this);
		methodCallContextSwitcher.methodCall("serverInterrupted");
		try {
			resourceAllocImpl.serverInterrupted(server);
		} finally {
			methodCallContextSwitcher.delete();
		}
	}

	private void updateDisplayString() {
		getDisplayString().setTagArg("t", 0, waitingForAllocation.size()+"/"+waitingForDeallocation.size());
	}

}
