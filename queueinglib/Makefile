#
# OMNeT++/OMNEST Makefile for libqueueinglib
#
# This file was generated with the command:
#  opp_makemake -f --deep --make-so -O out
#

# Name of target to be created (-o option)
TARGET = libqueueinglib$(SHARED_LIB_SUFFIX)

# C++ include paths (with -I)
INCLUDE_PATH = -I.

# Additional object and library files to link with
EXTRA_OBJS =

# Additional libraries (-L, -l options)
LIBS =

# Output directory
PROJECT_OUTPUT_DIR = out
PROJECTRELATIVE_PATH =
O = $(PROJECT_OUTPUT_DIR)/$(CONFIGNAME)/$(PROJECTRELATIVE_PATH)

# Object files for local .cc and .msg files
OBJS = \
    $O/ResourceBasedQueue.o \
    $O/Merge.o \
    $O/PassiveQueue.o \
    $O/Sink.o \
    $O/ResourcePool.o \
    $O/Job.o \
    $O/Classifier.o \
    $O/Clone.o \
    $O/QueueingUtils.o \
    $O/Allocate.o \
    $O/Router.o \
    $O/Fork.o \
    $O/JobList.o \
    $O/Server.o \
    $O/Deallocate.o \
    $O/Queue.o \
    $O/SelectionStrategies.o \
    $O/Delay.o \
    $O/Join.o \
    $O/Source.o \
    $O/PacketSender.o \
    $O/Job_m.o

# Message files
MSGFILES = \
    Job.msg

#------------------------------------------------------------------------------

# Pull in OMNeT++ configuration (Makefile.inc or configuser.vc)

ifneq ("$(OMNETPP_CONFIGFILE)","")
CONFIGFILE = $(OMNETPP_CONFIGFILE)
else
ifneq ("$(OMNETPP_ROOT)","")
CONFIGFILE = $(OMNETPP_ROOT)/Makefile.inc
else
CONFIGFILE = $(shell opp_configfilepath)
endif
endif

ifeq ("$(wildcard $(CONFIGFILE))","")
$(error Config file '$(CONFIGFILE)' does not exist -- add the OMNeT++ bin directory to the path so that opp_configfilepath can be found, or set the OMNETPP_CONFIGFILE variable to point to Makefile.inc)
endif

include $(CONFIGFILE)

# Simulation kernel and user interface libraries
OMNETPP_LIB_SUBDIR = $(OMNETPP_LIB_DIR)/$(TOOLCHAIN_NAME)
OMNETPP_LIBS = -L"$(OMNETPP_LIB_SUBDIR)" -L"$(OMNETPP_LIB_DIR)" -loppenvir$D $(KERNEL_LIBS) $(SYS_LIBS)

COPTS = $(CFLAGS)  $(INCLUDE_PATH) -I$(OMNETPP_INCL_DIR)
MSGCOPTS = $(INCLUDE_PATH)

#------------------------------------------------------------------------------
# User-supplied makefile fragment(s)
# >>>
# <<<
#------------------------------------------------------------------------------

# Main target
all: $(TARGET)

$(TARGET) : $O/$(TARGET)
	ln -s -f $O/$(TARGET) .

$O/$(TARGET): $(OBJS)  $(wildcard $(EXTRA_OBJS)) Makefile
	@mkdir -p $O
	$(SHLIB_LD) -o $O/$(TARGET)  $(OBJS) $(EXTRA_OBJS) $(LIBS) $(OMNETPP_LIBS) $(LDFLAGS)

.PHONY:

.SUFFIXES: .cc

$O/%.o: %.cc
	@mkdir -p $(dir $@)
	$(CXX) -c $(COPTS) -o $@ $<

%_m.cc %_m.h: %.msg
	$(MSGC) -s _m.cc $(MSGCOPTS) $?

msgheaders: $(MSGFILES:.msg=_m.h)

clean:
	-rm -rf $O
	-rm -f queueinglib queueinglib.exe libqueueinglib.so libqueueinglib.a libqueueinglib.dll libqueueinglib.dylib
	-rm -f ./*_m.cc ./*_m.h

cleanall: clean
	-rm -rf $(PROJECT_OUTPUT_DIR)

depend:
	$(MAKEDEPEND) $(INCLUDE_PATH) -f Makefile -P\$$O/ -- $(MSG_CC_FILES)  ./*.cc

# DO NOT DELETE THIS LINE -- make depend depends on it.
$O/Router.o: Router.cc \
	Router.h \
	QueueingDefs.h
$O/Delay.o: Delay.cc \
	Job_m.h \
	QueueingDefs.h \
	Delay.h \
	Job.h
$O/QueueingUtils.o: QueueingUtils.cc \
	QueueingDefs.h \
	QueueingUtils.h
$O/PassiveQueue.o: PassiveQueue.cc \
	Job_m.h \
	PassiveQueue.h \
	IPassiveQueue.h \
	IServer.h \
	QueueingDefs.h \
	SelectionStrategies.h \
	Job.h
$O/ResourceBasedQueue.o: ResourceBasedQueue.cc \
	Job_m.h \
	ResourceBasedQueue.h \
	QueueingDefs.h \
	IResourcePool.h \
	Job.h
$O/Allocate.o: Allocate.cc \
	Job_m.h \
	QueueingDefs.h \
	Allocate.h \
	ResourcePool.h \
	IResourcePool.h \
	Job.h
$O/JobList.o: JobList.cc \
	Job_m.h \
	JobList.h \
	QueueingDefs.h \
	Job.h
$O/Fork.o: Fork.cc \
	Job_m.h \
	Fork.h \
	QueueingDefs.h \
	Job.h
$O/Merge.o: Merge.cc \
	QueueingDefs.h \
	Merge.h
$O/SelectionStrategies.o: SelectionStrategies.cc \
	PassiveQueue.h \
	IPassiveQueue.h \
	Server.h \
	IServer.h \
	QueueingDefs.h \
	SelectionStrategies.h
$O/Job.o: Job.cc \
	Job_m.h \
	JobList.h \
	QueueingDefs.h \
	Job.h
$O/Job_m.o: Job_m.cc \
	Job_m.h \
	QueueingDefs.h
$O/Server.o: Server.cc \
	Job_m.h \
	IPassiveQueue.h \
	Server.h \
	IServer.h \
	QueueingDefs.h \
	SelectionStrategies.h \
	Job.h
$O/Queue.o: Queue.cc \
	Job_m.h \
	Queue.h \
	QueueingDefs.h \
	Job.h
$O/Classifier.o: Classifier.cc \
	Job_m.h \
	QueueingDefs.h \
	QueueingUtils.h \
	Job.h \
	Classifier.h
$O/Join.o: Join.cc \
	Job_m.h \
	QueueingDefs.h \
	Job.h \
	Join.h
$O/Clone.o: Clone.cc \
	Job_m.h \
	QueueingDefs.h \
	Job.h \
	Clone.h
$O/Source.o: Source.cc \
	Job_m.h \
	QueueingDefs.h \
	Source.h \
	Job.h
$O/Deallocate.o: Deallocate.cc \
	Deallocate.h \
	QueueingDefs.h \
	ResourcePool.h \
	IResourcePool.h
$O/Sink.o: Sink.cc \
	Job_m.h \
	Sink.h \
	QueueingDefs.h \
	Job.h
$O/ResourcePool.o: ResourcePool.cc \
	QueueingDefs.h \
	ResourcePool.h \
	IResourcePool.h
$O/PacketSender.o: PacketSender.cc \
	PacketSender.h

