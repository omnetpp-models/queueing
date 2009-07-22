package org.omnetpp.jqueue;

public interface IAddressablePool extends IResourcePool {
	int getCapacity();
	void setCapacity(int capacity); 

	void beginTransaction();  // only one transaction at a time 
	void rollback();          // deletes all blocks in current transaction
	void commit();
	
	int allocate(int address, int length);  // returns address or -1 on failure 
	int allocate(int length);  // strategy best fit, first fit. returns address or -1 on failure
	void deallocate(int address);  // deallocates the block starting at address (error if no such block exists)
}
