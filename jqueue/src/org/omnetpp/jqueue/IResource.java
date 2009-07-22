package org.omnetpp.jqueue;

public interface IResource {
	String getResourceClass();
	IResourcePool getResourcePool();
	double getAllocationTime();
}
