package org.omnetpp.jqueue.modules;

import JSimpleModule;

simple Sink extends JSimpleModule
{
    parameters:
        @display("i=block/sink");
    gates:
        input in[];
}
