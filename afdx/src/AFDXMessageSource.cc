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

#include "AFDXMessageSource.h"
#include "AFDXMessage_m.h"

namespace afdx {

Define_Module(AFDXMessageSource);

cMessage *AFDXMessageSource::createJob()
{
    AFDXMessage *msg = new AFDXMessage();
    msg->setKind(par("jobKind"));
    msg->setPriority(par("jobPriority"));
    // ADFX specific parameters
    msg->setNetworkId(par("networkId"));
    msg->setEquipmentId(par("equipmentId"));
    msg->setInterfaceId(par("interfaceId"));
    msg->setVirtualLinkId(par("virtualLinkId"));
    msg->setSeqNum(par("seqNum"));
    msg->setPartitionId(par("partitionId"));
    msg->setUdpSrcPort(par("udpSrcPort"));
    msg->setUdpDestPort(par("udpDestPort"));
    msg->setByteLength(par("packetLength").longValue());

    return msg;
}

}; // namespace afdx

