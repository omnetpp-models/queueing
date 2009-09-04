package org.omnetpp.jqueue;

import java.util.ArrayList;
import java.util.List;

import org.omnetpp.jqueue.IResourcePool.IResource;


// for the moment this is just a simple resource expression impl with label:amount pairs
// there is no resource selection strategy either. The first pool that can fulfill
// the requirement will be used for allocation
public class ResourceExpressionImpl implements IResourceExpression {
	class ResourceRequirement {
		public ResourceRequirement(IResourcePool pool, String expression) {
			this.pool = pool;
			this.expression = expression;
		}
		IResourcePool pool;
		String expression;  // this should contain the evaluated expression
	}
	
	List<ResourceRequirement> resReq = new ArrayList<ResourceRequirement>();
	List<IResourcePool> pools;
	
	public ResourceExpressionImpl(String expression, List<IResourcePool> pools) {
		this.pools = pools;
		// FIXME dummy implementation. (requires 1 from each listed resource)
		for(IResourcePool pool : pools)
			resReq.add(new ResourceRequirement(pool, "1"));
	}
	
	@Override
	public List<IResourcePool> getReferencedResourcePools() {
		return pools;
	}

	@Override
	public List<IResource> tryAllocateResourcesFor(IJob jobToTest) {
		List<IResource> result = new ArrayList<IResource>();
		boolean canAllocate = true;
		
		// the rr.expression should be evaluated only ONCE i.e. the eval result must be the same in both loop below
		
		// test if all resources are available 
		for (ResourceRequirement rr : resReq)
			canAllocate = canAllocate && rr.pool.isAvailable(rr.expression);
		
		if (!canAllocate)
			return null;

		// allocate the resources
		for (ResourceRequirement rr : resReq) {
			IResource res = rr.pool.allocate(rr.expression);
			result.add(res);
		}
				
		return result;
	}

}
