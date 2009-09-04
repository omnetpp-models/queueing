package org.omnetpp.jqueue;
import java.util.Set;


public interface INamedResourcePool extends IResourcePool {
	public interface INamedResource extends IResource {
		String getName();
	}	// TODO: capacity ???
	void setContents(Set<INamedResource> resources);
	Set<INamedResource> getContents();
	
	void allocate(Set<INamedResource> resources);
	void allocate(INamedResource resource);
	void deallocate(Set<INamedResource> resources); 
	void deallocate(INamedResource resource);
}
