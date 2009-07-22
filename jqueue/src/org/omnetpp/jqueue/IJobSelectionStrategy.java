package org.omnetpp.jqueue;
import java.util.List;

public interface IJobSelectionStrategy {
	List<IJob> selectJobs();
	void setJobList(List<IJob> jobs);
}
