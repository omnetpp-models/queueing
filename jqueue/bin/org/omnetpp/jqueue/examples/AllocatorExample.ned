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

package org.omnetpp.jqueue.examples;

import org.omnetpp.jqueue.modules.Source;
import org.omnetpp.jqueue.modules.Sink;
import org.omnetpp.jqueue.modules.ServerResourcePool;
import org.omnetpp.jqueue.modules.ResourceAllocator;
//
// TODO auto-generated module
//
network AllocatorExample
{
    @display("bgb=452,229");
    submodules:
        source: Source {
            @display("p=55,126");
            interArrivalTime = 1s;
        }
        sink: Sink {
            @display("p=342,126");
        }
        serverResourcePool: ServerResourcePool {
            @display("p=154,28");
            class = "cpu";
        }
        resourceAllocator: ResourceAllocator {
            @display("p=194,121");
            allocationExpression = "cpu:1";
        }
    connections:
        source.out --> resourceAllocator.in++;
        resourceAllocator.out --> sink.in++;
        serverResourcePool.grantResource++ --> { @display("ls=white,,da"); } --> resourceAllocator.usesResource++;
}
