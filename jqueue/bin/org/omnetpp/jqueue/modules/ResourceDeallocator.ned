//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License
// along with this program.  If not, see http://www.gnu.org/licenses/.
// 

package org.omnetpp.jqueue.modules;

//
// Deallocates the resources from the job. If configured to wait for active resources, then 
//
simple ResourceDeallocator
{
    @display("i=block/arrival");
    int capacity = default(-1);		// the maximal size of the queue where jobs are waiting for the expiry of active resources
    volatile string deallocationExpression;     // the expression describing which resources should be deallocaed and how
    bool queueOnDeallocationFailure = default(true);  // otherwise it is routed to the exception gate
    bool waitForFinish = default(true);  // should the module block the job and put it into the queue
                                         // util the active resouces are expired?  
    gates:
        input in[];
        output out;
        input usesResource[] @label(resource);
        output exception @loose;
}
