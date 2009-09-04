package org.omnetpp.jqueue;

import org.omnetpp.jqueue.IServerPool.IServer;

public interface IServerListener {
	void serverFinished(IServer server);    // called when server has completed processing
	void serverInterrupted(IServer server); // called when server has been manually deallocated before the task was completed
}
