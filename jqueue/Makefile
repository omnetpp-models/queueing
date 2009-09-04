#
# OMNeT++/OMNEST Makefile for jqueue
#
# This file was generated with the command:
#  opp_makemake -f --deep -O out -KJSIMPLE_PROJ=/home/rhornig/omnetpp-4.0/samples/jsimplemodule/jsimple
#

# Name of target to be created (-o option)
TARGET = jqueue$(EXE_SUFFIX)

# User interface (uncomment one) (-u option)
USERIF_LIBS = $(ALL_ENV_LIBS) # that is, $(TKENV_LIBS) $(CMDENV_LIBS)
#USERIF_LIBS = $(CMDENV_LIBS)
#USERIF_LIBS = $(TKENV_LIBS)

# C++ include paths (with -I)
INCLUDE_PATH = \
    -I. \
    -Ibin \
    -Ibin/org \
    -Ibin/org/omnetpp \
    -Ibin/org/omnetpp/jqueue \
    -Ibin/org/omnetpp/jqueue/examples \
    -Ibin/org/omnetpp/jqueue/modules \
    -Isrc \
    -Isrc/org \
    -Isrc/org/omnetpp \
    -Isrc/org/omnetpp/jqueue \
    -Isrc/org/omnetpp/jqueue/examples \
    -Isrc/org/omnetpp/jqueue/modules

# Additional object and library files to link with
EXTRA_OBJS =

# Additional libraries (-L, -l options)
LIBS =

# Output directory
PROJECT_OUTPUT_DIR = out
PROJECTRELATIVE_PATH =
O = $(PROJECT_OUTPUT_DIR)/$(CONFIGNAME)/$(PROJECTRELATIVE_PATH)

# Object files for local .cc and .msg files
OBJS =

# Message files
MSGFILES =

# Other makefile variables (-K)
JSIMPLE_PROJ=/home/rhornig/omnetpp-4.0/samples/jsimplemodule/jsimple

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
OMNETPP_LIBS = -L"$(OMNETPP_LIB_SUBDIR)" -L"$(OMNETPP_LIB_DIR)" $(USERIF_LIBS) $(KERNEL_LIBS) $(SYS_LIBS)

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
	$(CXX) $(LDFLAGS) -o $O/$(TARGET)  $(OBJS) $(EXTRA_OBJS) $(WHOLE_ARCHIVE_ON) $(LIBS) $(WHOLE_ARCHIVE_OFF) $(OMNETPP_LIBS)

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
	-rm -f jqueue jqueue.exe libjqueue.so libjqueue.a libjqueue.dll libjqueue.dylib
	-rm -f ./*_m.cc ./*_m.h
	-rm -f bin/*_m.cc bin/*_m.h
	-rm -f bin/org/*_m.cc bin/org/*_m.h
	-rm -f bin/org/omnetpp/*_m.cc bin/org/omnetpp/*_m.h
	-rm -f bin/org/omnetpp/jqueue/*_m.cc bin/org/omnetpp/jqueue/*_m.h
	-rm -f bin/org/omnetpp/jqueue/examples/*_m.cc bin/org/omnetpp/jqueue/examples/*_m.h
	-rm -f bin/org/omnetpp/jqueue/modules/*_m.cc bin/org/omnetpp/jqueue/modules/*_m.h
	-rm -f src/*_m.cc src/*_m.h
	-rm -f src/org/*_m.cc src/org/*_m.h
	-rm -f src/org/omnetpp/*_m.cc src/org/omnetpp/*_m.h
	-rm -f src/org/omnetpp/jqueue/*_m.cc src/org/omnetpp/jqueue/*_m.h
	-rm -f src/org/omnetpp/jqueue/examples/*_m.cc src/org/omnetpp/jqueue/examples/*_m.h
	-rm -f src/org/omnetpp/jqueue/modules/*_m.cc src/org/omnetpp/jqueue/modules/*_m.h

cleanall: clean
	-rm -rf $(PROJECT_OUTPUT_DIR)

depend:
	$(MAKEDEPEND) $(INCLUDE_PATH) -f Makefile -P\$$O/ -- $(MSG_CC_FILES)  ./*.cc bin/*.cc bin/org/*.cc bin/org/omnetpp/*.cc bin/org/omnetpp/jqueue/*.cc bin/org/omnetpp/jqueue/examples/*.cc bin/org/omnetpp/jqueue/modules/*.cc src/*.cc src/org/*.cc src/org/omnetpp/*.cc src/org/omnetpp/jqueue/*.cc src/org/omnetpp/jqueue/examples/*.cc src/org/omnetpp/jqueue/modules/*.cc

# DO NOT DELETE THIS LINE -- make depend depends on it.

