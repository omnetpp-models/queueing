package org.omnetpp.jqueue;

public interface IResourceChangeListener {
	void resourceChanged(IResourcePool pool);	// called on resource change (including server finished) 
}
