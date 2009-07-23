package org.omnetpp.jqueue;

public interface IServerPool extends IResourcePool {
	enum OverflowPolicy { ERROR, DROP, REFUSE};
	
	int getAmount();
	void setAmount(int amount); // error if grows over capacity
	int getCapacity();
	void setCapacity(int capacity); 
	OverflowPolicy getOverflowPolicy();
	void setOverflowPolicy(OverflowPolicy policy);

	IServer allocate(double timeToHold);  // automatically deallocated after timeToHold. allocator is notified on deallocation
	void deallocate(IServer server);  // only to deallocate prematurely
	double getUtilization();
	public void addServerListener(IServerListener listener);
	public void removeServerListener(IServerListener listener);
}
