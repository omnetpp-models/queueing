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

import JSimpleModule;


//
// Jobs entering the module try to allocate the needed resources in a single atomic transaction
// The job can leave the node if all the resources has been allocated (including active ones (CPU))
//
simple ResourceAllocator extends JSimpleModule
{
    @display("i=block/passiveq");
    int capacity = default(-1);		// the maximal size of the queue where jobs are waiting for resources
    								// if the capacity is reached, jobs are routed to the exception gate
    volatile string allocationExpression;     // the expression describing the required resources
    bool queueOnAllocationFailure = default(true);  // otherwise it is routed to the exception gate
    gates:
        input in[];
        output out;
        input usesResource[] @label(resource);
        output exception @loose;
}
