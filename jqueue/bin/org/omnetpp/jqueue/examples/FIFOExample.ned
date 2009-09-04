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

import org.omnetpp.jqueue.modules.FIFO;
import org.omnetpp.jqueue.modules.Sink;
import org.omnetpp.jqueue.modules.Source;


//
// TODO auto-generated module
//
network FIFOExample
{
    submodules:
        fifo: FIFO {
            @display("p=132,76");
        }
        sink: Sink {
            @display("p=218,74");
        }
        source: Source {
            @display("p=40,68");
        }
    connections:
        source.out --> fifo.in++;
        fifo.out --> sink.in++;
}
