//
// This file is part of an OMNeT++/OMNEST simulation example.
//
// Copyright (C) 2006-2008 OpenSim Ltd.
//
// This file is distributed WITHOUT ANY WARRANTY. See the file
// `license' for details on this and other legal matters.
//

#include "Server.h"
#include "Job.h"
#include "SelectionStrategies.h"
#include "IPassiveQueue.h"

namespace queueing {

Define_Module(Server);

Server::Server()
{
    selectionStrategy = NULL;
    jobServiced = NULL;
    reserved = false;
    endServiceMsg = NULL;
}

Server::~Server()
{
    delete selectionStrategy;
    delete jobServiced;
    cancelAndDelete(endServiceMsg);
}

void Server::initialize()
{
    scalarUtilizationStats.setName("utilization");
    prevEventTimeStamp = 0.0;

    endServiceMsg = new cMessage("end-service");
    jobServiced = NULL;
    reserved = false;
    selectionStrategy = SelectionStrategy::create(par("fetchingAlgorithm"), this, true);
    if (!selectionStrategy)
        error("invalid selection strategy");
}

void Server::handleMessage(cMessage *msg)
{
    scalarUtilizationStats.collect2((jobServiced != NULL ? 1 : 0), simTime()-prevEventTimeStamp);
    prevEventTimeStamp = simTime();

    if (msg==endServiceMsg)
    {
        ASSERT(jobServiced!=NULL && reserved);
        simtime_t d = simTime() - endServiceMsg->getSendingTime();
        jobServiced->setTotalServiceTime(jobServiced->getTotalServiceTime() + d);
        send(jobServiced, "out");
        jobServiced = NULL;
        reserved = false;

        if (ev.isGUI()) getDisplayString().setTagArg("i",1,"");

        // examine all input queues, and request a new job from a non empty queue
        int k = selectionStrategy->select();
        if (k >= 0)
        {
            EV << "requesting job from queue " << k << endl;
            cGate *gate = selectionStrategy->selectableGate(k);
            check_and_cast<IPassiveQueue *>(gate->getOwnerModule())->request(gate->getIndex());
            reserved = true;
        }
    }
    else
    {
        if (jobServiced)
            error("job arrived while already servicing one");

        if (!reserved)
            error("job arrived but the server was not correctly reserved");

        jobServiced = check_and_cast<Job *>(msg);
        reserved = true;

        simtime_t serviceTime = par("serviceTime");
        scheduleAt(simTime()+serviceTime, endServiceMsg);

        if (ev.isGUI()) getDisplayString().setTagArg("i",1,"cyan");
    }
}

void Server::finish()
{
    recordScalar("utilization", scalarUtilizationStats.getMean());
}

bool Server::isIdle()
{
    return !reserved;
}

void Server::reserve()
{
    Enter_Method("reserve()");

    if (reserved)
		error("trying to reserve a server which is already reserved");

    reserved = true;
}


}; //namespace

