package org.omnetpp.jqueue.modules;

import JSimpleModule;

simple Source extends JSimpleModule
{
    parameters:
        @display("i=block/source");
        int numJobs = default(-1);               // number of jobs to be generated (-1 means no limit)
        volatile double interArrivalTime @unit(s); // time between generated jobs
        string jobName = default("job");         // the base name of the generated job (will be the module name if left empty)
        volatile int jobKind = default(0);       // the kind attribute of the created job (used by classifers and other modules)
        volatile int jobPriority = default(0);   // priority of the job
        // TODO add other job 
        double startTime @unit(s) = default(interArrivalTime); // when the module sends out the first job
        double stopTime @unit(s) = default(-1s); // when the module stops the job generation (-1 means no limit)
    gates:
        output out;
}
