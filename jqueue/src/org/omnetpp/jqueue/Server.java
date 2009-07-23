package org.omnetpp.jqueue;

import org.omnetpp.simkernel.JMessage;

public class Server extends JMessage implements IServer {

	private IServerPool pool;
	private IJob ownerJob;
	
	public Server(IServerPool pool) {
		this.pool = pool;
	}
	
	@Override
	public double getAllocationTime() {
		return getSendingTime().dbl();
	}

	@Override
	public IJob getOwnerJob() {
		return ownerJob;
	}

	@Override
	public String getResourceClass() {
		return pool.getResourceClass();
	}

	@Override
	public IServerPool getResourcePool() {
		return pool;
	}

	@Override
	public void setOwnerJob(IJob ownerJob) {
		this.ownerJob = ownerJob;
	}

}
