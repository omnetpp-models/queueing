package org.omnetpp.jqueue;

public interface IResource {
	String getResourceClass();
	IResourcePool getResourcePool();
	double getAllocationTime();
	IJob getOwnerJob(); // null if the resource is not allocated for a job
	void setOwnerJob(IJob ownerJob);  // ONLY TO BE CALLED FROM IJob
}
