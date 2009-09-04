package org.omnetpp.jqueue;

public interface IResourcePool {
	public interface IResource {
		String getResourceClass();
		IResourcePool getResourcePool();
		double getAllocationTime();
		IJob getOwnerJob(); // null if the resource is not allocated for a job
		void setOwnerJob(IJob ownerJob);  // ONLY TO BE CALLED FROM IJob
	}
	
	String getResourceClass();
	
	boolean isAvailable(String expression);
	IResource allocate(String expression);
	void deallocate(IResource resource);
	
	void addResourceChangeListener(IResourceChangeListener listener);
	void removeResourceChangeListener(IResourceChangeListener listener);
}
