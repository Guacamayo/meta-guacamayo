#!/bin/bash
# (c) 2009 - 2012 Koen Kooi <koen@dominion.thruhere.net>
# This script will take a set of directories with patches and make a git tree out of it
# After all the patches are applied it will output a SRC_URI fragment you can copy/paste into a recipe
set -e

TAG="v3.0.17"
EXTRATAG=""
PATCHPATH=$(dirname $0)

git am --abort || echo "Do you need to make sure the patches apply cleanly first?"
git reset --hard ${TAG}
rm export -rf

previous=${TAG}
PATCHSET="pm-wip/voltdm pm-wip/cpufreq beagle madc sakoman sgx ulcd omap4 misc usb"

# apply patches
for patchset in ${PATCHSET} ; do
	git am $PATCHPATH/$patchset/*
	git tag "${TAG}-${patchset}${EXTRATAG}" -f
done

# export patches and output SRC_URI for them
for patchset in ${PATCHSET} ; do
	mkdir export/$patchset -p
	( cd export/$patchset && git format-patch ${previous}..${TAG}-${patchset}${EXTRATAG} >& /dev/null && for i in *.patch ; do echo "            file://${patchset}/$i \\" ; done )
	previous=${TAG}-${patchset}${EXTRATAG}
done
