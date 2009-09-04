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
// TODO auto-generated module
//
simple Split
{
	    @display("i=block/fork");
    	int splitInto = default(sizeof(out));  // how many sub-jobs should be created
    	string tag;	// This tag is attached to the sub-jobs. A parameter with the given name is attached
    	            // The value of the parameter is the ID (0..splitInto-1) of the subJob (integer) 
    gates:
        input in[];
        output out;
}
