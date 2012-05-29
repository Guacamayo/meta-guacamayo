# the 1.10.6 tag
SRCREV = "e60e74e46fb46b11758ef341352baed240f56ae9"
PV = "1.10.6+git${SRCPV}"
PR = "${INCPR}.1"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://git.gnome.org/clutter;protocol=git;branch=master \
           file://${GUACABASE}/meta/recipes-graphics/clutter/clutter/enable_tests-654c26a1301c9bc5f8e3e5e3b68af5eb1b2e0673.patch;rev=654c26a1301c9bc5f8e3e5e3b68af5eb1b2e0673 \
           file://${GUACABASE}/meta/recipes-graphics/clutter/clutter/enable_tests.patch;notrev=654c26a1301c9bc5f8e3e5e3b68af5eb1b2e0673 "

S = "${WORKDIR}/git"
