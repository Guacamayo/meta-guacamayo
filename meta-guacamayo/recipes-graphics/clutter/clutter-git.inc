# the 1.10.2 tag
SRCREV = "62cffec9036f6a24736dc47914844fe9336c8ce4"
PV = "1.10.2+git${SRCPV}"
PR = "${INCPR}.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://git.gnome.org/clutter;protocol=git;branch=master \
           file://${GUACABASE}/meta/recipes-graphics/clutter/clutter/enable_tests-654c26a1301c9bc5f8e3e5e3b68af5eb1b2e0673.patch;rev=654c26a1301c9bc5f8e3e5e3b68af5eb1b2e0673 \
           file://${GUACABASE}/meta/recipes-graphics/clutter/clutter/enable_tests.patch;notrev=654c26a1301c9bc5f8e3e5e3b68af5eb1b2e0673 "

S = "${WORKDIR}/git"
