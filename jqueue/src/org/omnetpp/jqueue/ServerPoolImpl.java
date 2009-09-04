package org.omnetpp.jqueue;

import java.util.ArrayList;
import java.util.List;

import org.omnetpp.simkernel.JMessage;
import org.omnetpp.simkernel.Simkernel;
import org.omnetpp.simkernel.cSimpleModule;

public class ServerPoolImpl extends ResourcePoolImpl implements IServerPool {

	private cSimpleModule ownerModule;
	int amount = 1;
	int capacity = 1;

	List<IServerListener> serverListeners = new ArrayList<IServerListener>();

	@Override
	public void addServerListener(IServerListener listener) {
		serverListeners.add(listener);
	}

	@Override
	public void removeServerListener(IServerListener listener) {
		serverListeners.remove(listener);
	}
	
	protected void fireServerFinished(IServer server) {
		// TODO implement allocator selection strategy
		for (IServerListener listener : serverListeners.toArray(new IServerListener[0]))
			listener.serverFinished(server);
	}

	protected void fireServerInterrupted(IServer server) {
		// TODO implement allocator selection strategy
		for (IServerListener listener : serverListeners.toArray(new IServerListener[0]))
			listener.serverInterrupted(server);
	}

	public ServerPoolImpl(String resourceClass, cSimpleModule ownerModule) {
		super(resourceClass);
		this.ownerModule = ownerModule;
	}

	@Override
	public IResource allocate(String expression) {
		if (amount == 0) 
			throw new IllegalStateException("No free servers");

		double timeToHold = Double.valueOf(expression);
		amount--;
		ServerImpl server = new ServerImpl(this);
		server.setName("server - timout");
		ownerModule.scheduleAt(Simkernel.simTime().add(timeToHold), server);
		fireResourceChange();
		return server ;
	}

	@Override
	public void deallocate(IResource server) {
		amount++;
		// TODO check capacity
		JMessage msg = (JMessage)server;
		if (msg.isScheduled()) {
			ownerModule.cancelEvent(msg);
			fireServerInterrupted((IServer)server);
		} else {
			fireServerFinished((IServer)server);
		}
		fireResourceChange();
	}

	@Override
	public boolean isAvailable(String expression) {
		return amount > 0;
	}

	
	@Override
	public int getAmount() {
		return amount;
	}

	@Override
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public void setCapacity(int capacity) {
		this.capacity = capacity;
		
	}
	
	@Override
	public OverflowPolicy getOverflowPolicy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOverflowPolicy(OverflowPolicy policy) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getUtilization() {
		// FIXME
		return 0.5;
	}
}
