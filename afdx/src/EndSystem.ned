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

package org.omnetpp.queueing.afdx;

import org.omnetpp.queueing.Clone;
import org.omnetpp.queueing.Sink;
import org.omnetpp.queueing.PassiveQueue;
import org.omnetpp.queueing.Merge;
//
// This module represents an EndSystem device in an AFDX network. It has two 
// separate ethernet interfaces which should be attached to different networks
//
module EndSystem
{
    @display("i=device/device;bgb=413,287");
    gates:
        inout ethPortA @labels(eth100);
        inout ethPortB @labels(eth100);
    submodules:
        trafficGen1: AFDXMessageSource {
            @display("p=86,62");
        }
        redundancy: Clone {
            @display("p=193,62");
        }
        sink: Sink {
            @display("p=173,227");
        }
        macB: MAC {
            @display("p=351,150");
        }
        macA: MAC {
            @display("p=351,75");
        }
        txQueueA: PassiveQueue {
            @display("p=272,75");
        }
        txQueueB: PassiveQueue {
            @display("p=272,150");
        }
        merge: Merge {
            @display("p=284,227");
        }
    connections:
        trafficGen1.out --> redundancy.in++;
        redundancy.out++ --> txQueueB.in++;
        redundancy.out++ --> txQueueA.in++;
        ethPortA <--> macA.eth;
        ethPortB <--> macB.eth;
        txQueueA.out++ --> macA.in++;
        txQueueB.out++ --> macB.in++;
        macA.out --> merge.in++;
        macB.out --> merge.in++;
        merge.out --> sink.in++;
}
