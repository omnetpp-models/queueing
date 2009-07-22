package org.omnetpp.jqueue;

public interface IResourceAmountPool extends IResourcePool {
	enum OverflowPolicy { ERROR, DROP, REFUSE};
	
	double getAmount();
	void setAmount(double amount); // error if grows over capacity
	double getCapacity();
	void setCapacity(double capacity); 
	OverflowPolicy getOverflowPolicy();
	void setOverflowPolicy(OverflowPolicy policy);

	void allocate(double amount);
	void deallocate(double amount);  // error if grows over capacity
}
