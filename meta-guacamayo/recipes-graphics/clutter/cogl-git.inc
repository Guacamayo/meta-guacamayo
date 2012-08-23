# the 1.10.4 tag
SRCREV = "13861b710fc2e78e9f3c6042967817d5da7df9a0"
PV = "1.10.4+git${SRCPV}"
PR = "${INCPR}.1"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI += "git://git.gnome.org/cogl;protocol=git;branch=master"

S = "${WORKDIR}/git"

AUTOTOOLS_AUXDIR = "${S}/build"

