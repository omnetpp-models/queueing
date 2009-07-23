package org.omnetpp.jqueue.modules;

import org.omnetpp.jqueue.Job;
import org.omnetpp.simkernel.JSimpleModule;
import org.omnetpp.simkernel.SimTime;
import org.omnetpp.simkernel.Simkernel;
import org.omnetpp.simkernel.cMessage;

/**
 * Sends a message periodically.
 */
public class Source extends JSimpleModule {
    private int counter = 0;

    protected void initialize() {
        ev.println("initialize of "+getFullPath());
        cMessage msg = new cMessage("timeout-"+getFullName());
        scheduleAt(simTime().add(new SimTime(1)), msg);
    }

    protected void handleMessage(cMessage msg) {
        ev.println("send #" + ++counter);

        Job job = new Job("job-" + counter);
        send(job, "out");

        ev.println("scheduling next send");
        scheduleAt(simTime().add(new SimTime(Simkernel.exponential(1))), msg);
    }

    protected void finish() {
        ev.println("finish of "+getFullPath());
    }
}
