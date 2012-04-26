#!/bin/bash
# (c) 2009 - 2012 Koen Kooi <koen@dominion.thruhere.net>
# This script will take a set of directories with patches and make a git tree out of it
# After all the patches are applied it will output a SRC_URI fragment you can copy/paste into a recipe
set -e

# don't use builtin 'echo' from /bin/sh
export ECHO="$(which echo)"

git tag -f beaglebone-v3.2 e8004dad869568692ca2a45c04e8464ce48cc4d7

TAG="beaglebone-v3.2"
EXTRATAG=""
PATCHPATH=$(dirname $0)

git am --abort || echo "Do you need to make sure the patches apply cleanly first?"
git reset --hard ${TAG}

rm -Rf export

previous=${TAG}
PATCHSET="3.2.1 3.2.2 3.2.3 3.2.4 3.2.5 3.2.6 3.2.7 3.2.8 3.2.9 3.2.10 3.2.11 3.2.12 3.2.13 3.2.14 beaglebone"

# apply patches
for patchset in ${PATCHSET} ; do
	for patch in $(ls -1 $PATCHPATH/$patchset/* | sort -n) ; do
		$ECHO -n "$patch: "
		git am -q $patch && echo applied || exit 1
	done
	git tag "${TAG}-${patchset}${EXTRATAG}" -f
done

# export patches and output SRC_URI for them
for patchset in ${PATCHSET} ; do
	mkdir -p export/$patchset 
	( cd export/$patchset && git format-patch ${previous}..${TAG}-${patchset}${EXTRATAG} >& /dev/null && for i in *.patch ; do echo "\tfile://${patchset}/$i \\" ; done )
	previous=${TAG}-${patchset}${EXTRATAG}
done > src-uri.txt
