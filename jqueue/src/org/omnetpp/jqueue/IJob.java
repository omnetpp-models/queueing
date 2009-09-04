package org.omnetpp.jqueue;

import java.util.List;
import java.util.Set;

import org.omnetpp.jqueue.IResourcePool.IResource;

public interface IJob {
	int getPriority();
	void setPriority(int priority);
	
	String getName();
	void setName(String name);
	
	void addResource(IResource resource, String label);  
	void addResources(List<IResource> resources, String label); 
	List<IResource> removeResources(String label); 
	void removeResources(List<IResource> resources);
	void removeResource(IResource resource);
	
	List<IResource> getResources(String label);
	List<IResource> getResources();
	Set<String> getLabels();
	
}
