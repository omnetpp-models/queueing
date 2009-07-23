package org.omnetpp.jqueue;

import java.util.List;



public abstract class ResourceAllocator implements IResourceChangeListener {
	
	private IResourceExpression resourceExpression;
	private IJobAllocationStrategy jobAllocStrat;
	private String resourceLabel;
	
	public void tryToAllocateResources() {
		List<IJob> satisfiedJobs = jobAllocStrat.allocateResourcesForJobs(selectJobs(), resourceExpression, resourceLabel);
		if(!satisfiedJobs.isEmpty())
			onAllocation(satisfiedJobs);
	}

	protected void onAllocation(List<IJob> job) {
		
	}
	
	protected abstract List<IJob> selectJobs(); 
	
	@Override
	public void resourceChanged(IResourcePool pool) {
		tryToAllocateResources();
	}

	@Override
	public void serverFinished(IServer server) {
	}


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


}
