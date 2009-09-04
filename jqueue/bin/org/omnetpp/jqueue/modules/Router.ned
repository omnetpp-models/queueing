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
// Router. Sends out the job (or a copy of it) on each output gate where the expression evaluates to true
//
simple Router
{
	    @display("i=block/routing");
        volatile bool expression = default(false); // evaluated for each incoming job and each connected output connection 
    gates:
        input in[];
        output out[];
}
