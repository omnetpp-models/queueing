package org.omnetpp.jqueue;
import java.util.List;



public interface IResourceExpression {
	// return null if allocation is unsuccessful, otherwise the list of allocated resources
	List<IResource> tryAllocateResourcesFor(IJob jobToTest);
	List<IResourcePool> getReferencedResourcePools();
}
