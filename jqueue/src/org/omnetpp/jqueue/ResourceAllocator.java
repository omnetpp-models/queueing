package org.omnetpp.jqueue;

import java.util.ArrayList;
import java.util.List;



public abstract class ResourceAllocator implements IResourceChangeListener {
	
	private IResourceExpression resourceExpression;
	private String resourceLabel;
	
	public void tryToAllocateResources() {
		List<IJob> jobsToTest = selectJobs();
		List<IJob> satisfiedJobs = new ArrayList<IJob>();
		for (IJob job : jobsToTest) {
			List<IResource> resources = resourceExpression.tryAllocateResourcesFor(job);
			if (resources != null) {
				job.addResources(resources, getResourceLabel());
				satisfiedJobs.add(job);
			}
		}
		
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


}
