package org.omnetpp.jqueue;

import java.util.ArrayList;
import java.util.List;

public class ResourcePool implements IResourcePool {

	private String resourceClass;
	
	List<IResourceChangeListener> listeners = new ArrayList<IResourceChangeListener>();
	
	public ResourcePool(String resourceClass) {
		this.resourceClass = resourceClass;
	}
	
	@Override
	public String getResourceClass() {
		return resourceClass;
	}

	@Override
	public void addResourceChangeListener(IResourceChangeListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeResourceChangeListener(IResourceChangeListener listener) {
		listeners.remove(listener);
	}
	
	protected void fireResourceChange() {
		// TODO implement allocator selection strategy
		for (IResourceChangeListener listener : listeners.toArray(new IResourceChangeListener[0]))
			listener.resourceChanged(this);
	}

}
