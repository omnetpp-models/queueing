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

#ifndef __REDUNDANCYCHECKER_H__
#define __REDUNDANCYCHECKER_H__

#include <omnetpp.h>

namespace afdx {

/**
 * TODO - Generated class
 */
class RedundancyChecker : public cSimpleModule
{
  private:
	bool enabled;
	simtime_t skewMax;
	simtime_t lastFrameReceived;
	int nextSeqExpected;
  protected:
    virtual void initialize();
    virtual void handleMessage(cMessage *msg);
};

}; // namespace afdx

#endif
