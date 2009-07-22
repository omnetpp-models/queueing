package org.omnetpp.jqueue;

public interface IResourcePool {
	String getResourceClass();
	void addResourceChangeListener(IResourceChangeListener listener);
	void removeResourceChangeListener(IResourceChangeListener listener);
}
