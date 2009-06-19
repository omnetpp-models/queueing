//
// This file is part of an OMNeT++/OMNEST simulation example.
//
// Copyright (C) 2006-2008 OpenSim Ltd.
//
// This file is distributed WITHOUT ANY WARRANTY. See the file
// `license' for details on this and other legal matters.
//

#include "QueueingUtils.h"

namespace queueing {

int findField(cObject *obj, const char *fieldName)
{
    cClassDescriptor *descriptor = obj->getDescriptor();
    int numFields = descriptor->getFieldCount(obj);
    for (int i= 0; i<numFields; ++i)
    	if (strcmp(fieldName, descriptor->getFieldName(obj, i)) == 0)
    		return i;

    return -1;
}

std::string getFieldByName(cObject *obj, const char *fieldName)
{
    int i = findField(obj, fieldName);
    if (i == -1)
    	opp_error("field \"%s\" not found in \"%s\"", fieldName, obj->getClassName());

    char buff[256];
    obj->getDescriptor()->getFieldAsString(obj, i, 0, buff, 256);
    return buff;
}

long getFieldAsLong(cObject *obj, const char *fieldName)
{
    char *endbuff;
	std::string buff = getFieldByName(obj, fieldName);
    long result = strtol(buff.c_str(), &endbuff, 10);
    if (endbuff != buff.c_str() + buff.size() )
    	opp_error("field \"%s\" contains \"%s\", but it should contain an integer", fieldName, buff.c_str());

    return result;
}

}
