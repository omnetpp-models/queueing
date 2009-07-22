package org.omnetpp.jqueue;

public interface IResourceChangeListener {
	void resourceChanged(IResourcePool pool);	// called on resource change (including server finished) 
	void serverFinished(IServer server);		// called when server has finished processing
}
