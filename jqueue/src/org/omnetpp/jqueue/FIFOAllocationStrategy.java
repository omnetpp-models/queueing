package org.omnetpp.jqueue;

import java.util.ArrayList;
import java.util.List;

public class FIFOAllocationStrategy implements IJobAllocationStrategy {

	@Override
	public List<IJob> allocateResourcesForJobs(List<IJob> jobsToTest,
			IResourceExpression expression, String allocationLabel) {
		
		List<IJob> satisfiedJobs = new ArrayList<IJob>();
		for (IJob job : jobsToTest) {
			List<IResource> resources = expression.tryAllocateResourcesFor(job);
			if (resources != null) {
				job.addResources(resources, allocationLabel);
				satisfiedJobs.add(job);
			} else
				break;

		}

		return satisfiedJobs;
	}

}
