# This top level Makefile runs make in all subdirectories with Makefiles in them
# Original taken from https://stackoverflow.com/a/17845120
#
# TOPTARGETS define all make commands which can be used in all Makefiles within this repo
TOPTARGETS := clean build test publish-dev publish-prod

SUBDIRS := $(shell find . -name "Makefile" -d 2 | xargs dirname)

.PHONY: $(TOPTARGETS) $(SUBDIRS)
$(TOPTARGETS): $(SUBDIRS)
$(SUBDIRS):
	$(MAKE) -C $@ $(MAKECMDGOALS)

