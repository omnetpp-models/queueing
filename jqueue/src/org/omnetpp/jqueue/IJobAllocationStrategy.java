package org.omnetpp.jqueue;

import java.util.List;

public interface IJobAllocationStrategy {

	List<IJob> allocateResourcesForJobs(List<IJob> jobs, IResourceExpression expression, String allocationLabel);
	
}
