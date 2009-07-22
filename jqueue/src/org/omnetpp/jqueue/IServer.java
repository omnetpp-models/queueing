package org.omnetpp.jqueue;

public interface IServer extends IResource {
	boolean isActive();  // set to false by the ServerPool when the server was deallocated
}
