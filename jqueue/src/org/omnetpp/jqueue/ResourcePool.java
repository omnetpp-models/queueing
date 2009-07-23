package org.omnetpp.jqueue;

public class ResourcePool implements IResourcePool {

	String resourceClass;
	
	public ResourcePool(String resourceClass) {
		this.resourceClass = resourceClass;
	}
	
	@Override
	public String getResourceClass() {
		return resourceClass;
	}

	@Override
	public void addResourceChangeListener(IResourceChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeResourceChangeListener(IResourceChangeListener listener) {
		// TODO Auto-generated method stub

	}

}
