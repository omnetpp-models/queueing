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
// Evaluates the WHILE expression for each incoming message. If the result is true the,
// message is passed on the continue gate. 
//
simple Loop
{
    @display("i=block/opticaldelay");
    string name;
    volatile bool while = default(false);   // an expression that is evaluated for each incoming message
    											// on true, the message is passed to the "finished" gate. 
    gates:
        input in[];
        input again[];
        output continue;
        output finished;
}
