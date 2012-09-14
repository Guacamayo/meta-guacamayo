# the 1.11.2 tag
SRCREV = "c1d2298e30d515347c5e3f7b29c296280f80226b"
PV = "1.11.2+git${SRCPV}"
INCPR_append = ".0"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI = "git://git.gnome.org/clutter;protocol=git;branch=master \
           file://${COREBASE}/meta/recipes-graphics/clutter/clutter/enable_tests-654c26a1301c9bc5f8e3e5e3b68af5eb1b2e0673.patch;rev=654c26a1301c9bc5f8e3e5e3b68af5eb1b2e0673 \
           file://${COREBASE}/meta/recipes-graphics/clutter/clutter/enable_tests.patch;notrev=654c26a1301c9bc5f8e3e5e3b68af5eb1b2e0673 "

S = "${WORKDIR}/git"
