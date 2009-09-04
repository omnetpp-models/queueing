package org.omnetpp.jqueue;

import java.util.List;

import org.omnetpp.jqueue.IServerPool.IServer;



public abstract class ResourceAllocatorImpl implements IResourceChangeListener, IServerListener {
	
	private IResourceExpression resourceExpression;
	private IJobAllocationStrategy jobAllocStrat;
	private String resourceLabel;
	
	public void tryToAllocateResources() {
		List<IJob> satisfiedJobs = jobAllocStrat.allocateResourcesForJobs(getJobList(), resourceExpression, resourceLabel);
		if(!satisfiedJobs.isEmpty())
			onAllocation(satisfiedJobs);
	}

	protected abstract void onAllocation(List<IJob> job);
	protected abstract List<IJob> getJobList(); 
	
	@Override
	public void resourceChanged(IResourcePool pool) {
		tryToAllocateResources();
	}

	@Override
	public void serverFinished(IServer server) {
		assert(server.getOwnerJob() != null);
		IJob job = server.getOwnerJob();
		job.removeResource(server);
		onServerFinished(job, server);
	}

	@Override
	public void serverInterrupted(IServer server) {
		assert(server.getOwnerJob() != null);
		IJob job = server.getOwnerJob();
		job.removeResource(server);
		onServerInterrupted(job, server);
	}

	protected abstract void onServerFinished(IJob job, IServer server);
	protected abstract void onServerInterrupted(IJob job, IServer server);
	
	public void setResourceExpression(IResourceExpression resourceExpression) {
		this.resourceExpression = resourceExpression;
	}

	public IResourceExpression getResourceExpression() {
		return resourceExpression;
	}

	public void setResourceLabel(String resourceLabel) {
		this.resourceLabel = resourceLabel;
	}

	public String getResourceLabel() {
		return resourceLabel;
	}

	public void setJobAllocStrat(IJobAllocationStrategy jobAllocStrat) {
		this.jobAllocStrat = jobAllocStrat;
	}

	public IJobAllocationStrategy getJobAllocStrat() {
		return jobAllocStrat;
	}

	public void hookResourceChangeListeners() {
		for (IResourcePool pool : resourceExpression.getReferencedResourcePools()) {
			pool.addResourceChangeListener(this);
			if (pool instanceof IServerPool)
				((IServerPool) pool).addServerListener(this);
			
		}
	}

	public void unhookResourceChangeListeners() {
		for (IResourcePool pool : resourceExpression.getReferencedResourcePools()) {
			pool.removeResourceChangeListener(this);
			if (pool instanceof IServerPool)
				((IServerPool) pool).removeServerListener(this);
		}
	}
}
