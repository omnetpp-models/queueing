package org.omnetpp.jqueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.omnetpp.simkernel.JMessage;

public class Job extends JMessage implements IJob {
	private int priority;
	private Map<String, List<IResource>> resourceMap = new HashMap<String, List<IResource>>();

	public Job(String name) {
		super(name);
	}

	@Override
	public int getPriority() {
		return priority;
	}
	
	@Override
	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public void addResource(IResource resource, String label) {
		if (!resourceMap.containsKey(label))
			resourceMap.put(label, new ArrayList<IResource>());
		resourceMap.get(label).add(resource);
		resource.setOwnerJob(this);
	}

	@Override
	public void addResources(List<IResource> resources, String label) {
		if (!resourceMap.containsKey(label))
			resourceMap.put(label, new ArrayList<IResource>());
		resourceMap.get(label).addAll(resources);
		setOwner(resources, this);
	}

	@Override
	public Set<String> getLabels() {
		return resourceMap.keySet();
	}

	@Override
	public List<IResource> getResources(String label) {
		return resourceMap.get(label);
	}

	@Override
	public List<IResource> getResources() {
		List<IResource> result = new ArrayList<IResource>();
		for (List<IResource> list : resourceMap.values())
			result.addAll(list);
		return result;
	}

	@Override
	public void removeResource(IResource resource) {
		resource.setOwnerJob(null);
		for (List<IResource> list : resourceMap.values())
			list.remove(resource);
	}

	@Override
	public List<IResource> removeResources(String label) {
		List<IResource> removed = resourceMap.remove(label);
		if (removed != null)
			setOwner(removed, null);
		return removed;
	}

	@Override
	public void removeResources(List<IResource> resources) {
		setOwner(resources, null);
		for (List<IResource> list : resourceMap.values())
			list.removeAll(resources);
	}
	
	private void setOwner(List<IResource> resources, IJob job) {
		for (IResource res : resources) 
			res.setOwnerJob(job);
	}
	
	@Override
	public String toString() {
		return getName();
	}
}
