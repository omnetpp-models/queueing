package org.omnetpp.jqueue.modules;

import org.omnetpp.jqueue.IResourceChangeListener;
import org.omnetpp.jqueue.IServerListener;
import org.omnetpp.jqueue.IServerPool;
import org.omnetpp.jqueue.ServerPoolImpl;
import org.omnetpp.simkernel.JMessage;
import org.omnetpp.simkernel.JSimpleModule;
import org.omnetpp.simkernel.cMessage;
import org.omnetpp.simkernel.cMethodCallContextSwitcher;

public class ServerResourcePool extends JSimpleModule implements IServerPool {

	protected IServerPool serverPool;
	
	@Override
	protected void initialize() {
		serverPool = new ServerPoolImpl(par("class").str(), this) {
			@Override
			protected void fireResourceChange() {
				super.fireResourceChange();
				updateDisplayString();
			}
			
			@Override
			protected void fireServerFinished(IServer server) {
				super.fireServerFinished(server);
				updateDisplayString();
			}
			
			@Override
			protected void fireServerInterrupted(IServer server) {
				super.fireServerInterrupted(server);
				updateDisplayString();
			}
		};
		serverPool.setCapacity(par("capacity").longValue());
		serverPool.setAmount(serverPool.getCapacity());
		String overflowPolicy = par("overflowPolicy").str();
		if ("error".equalsIgnoreCase(overflowPolicy)) 
			serverPool.setOverflowPolicy(OverflowPolicy.ERROR);
		else if ("drop".equalsIgnoreCase(overflowPolicy)) 
			serverPool.setOverflowPolicy(OverflowPolicy.DROP);
		else if ("refuse".equalsIgnoreCase(overflowPolicy)) 
			serverPool.setOverflowPolicy(OverflowPolicy.REFUSE);

		updateDisplayString();

		// attach the resource change listeners
		int size = gateSize("grantResource");
		for (int i=0; i<size; ++i) {
			JSimpleModule mod = JSimpleModule.cast(gate("grantResource",i).getPathEndGate().getOwnerModule());
			if (mod instanceof IResourceChangeListener)
				addResourceChangeListener((IResourceChangeListener)mod);
			else if (mod instanceof IServerListener)
				addServerListener((IServerListener)mod);
			else
				error("Using a non-allocator module as a resource client ("+mod.getClassName()+")");
		}
		
		super.initialize();
	}

	@Override
	protected void handleMessage(cMessage msg) {
		JMessage jmsg = JMessage.cast(msg);
		assert(jmsg != null);
		assert(((IServer)jmsg).getResourcePool() == serverPool);
		
		if (jmsg instanceof IServer) {
			serverPool.deallocate((IServer)jmsg);
			updateDisplayString();
			}
	}

	// delegate the other methods to the implementation class
	public void addResourceChangeListener(IResourceChangeListener listener) {
		serverPool.addResourceChangeListener(listener);
	}


	public void addServerListener(IServerListener listener) {
		serverPool.addServerListener(listener);
	}

	public int getAmount() {
		return serverPool.getAmount();
	}


	public int getCapacity() {
		return serverPool.getCapacity();
	}


	public OverflowPolicy getOverflowPolicy() {
		return serverPool.getOverflowPolicy();
	}


	public String getResourceClass() {
		return serverPool.getResourceClass();
	}


	public double getUtilization() {
		return serverPool.getUtilization();
	}


	public void removeResourceChangeListener(IResourceChangeListener listener) {
		serverPool.removeResourceChangeListener(listener);
	}


	public void removeServerListener(IServerListener listener) {
		serverPool.removeServerListener(listener);
	}


	public void setAmount(int amount) {
		serverPool.setAmount(amount);
	}


	public void setCapacity(int capacity) {
		serverPool.setCapacity(capacity);
	}


	public void setOverflowPolicy(OverflowPolicy policy) {
		serverPool.setOverflowPolicy(policy);
	}

	public IResource allocate(String expression) {
		cMethodCallContextSwitcher methodCallContextSwitcher = new cMethodCallContextSwitcher(this);
		methodCallContextSwitcher.methodCall("allocate: "+expression);
		try {
			IResource resource = serverPool.allocate(expression);
			updateDisplayString();
			return resource;
		} finally {
			methodCallContextSwitcher.delete();
		}
	}

	public void deallocate(IResource resource) {
		cMethodCallContextSwitcher methodCallContextSwitcher = new cMethodCallContextSwitcher(this);
		methodCallContextSwitcher.methodCall("deallocate");
		try {
			serverPool.deallocate(resource);
			updateDisplayString();
		} finally {
			methodCallContextSwitcher.delete();
		}
	}

	public boolean isAvailable(String expression) {
		return serverPool.isAvailable(expression);
	}

	private void updateDisplayString() {
		getDisplayString().setTagArg("t", 0, "#"+serverPool.getAmount());
	}

}
