package org.omnetpp.jqueue;

public class ServerPool implements IServerPool {

	int amount;
	
	@Override
	public IServer allocate(double timeToHold) {
		// TODO Auto-generated method stub
		return new IServer() {

			@Override
			public boolean isActive() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public double getAllocationTime() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public String getResourceClass() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public IResourcePool getResourcePool() {
				// TODO Auto-generated method stub
				return null;
			}
			
		};
	}

	@Override
	public void deallocate(IServer server) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OverflowPolicy getOverflowPolicy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAmount(int amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCapacity(int capacity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOverflowPolicy(OverflowPolicy policy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addResourceChangeListener(IResourceChangeListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getResourceClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeResourceChangeListener(IResourceChangeListener listener) {
		// TODO Auto-generated method stub

	}

}
