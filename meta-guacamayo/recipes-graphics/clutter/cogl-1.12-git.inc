SRCREV = "cb834c95e98e02a249a9cc6ad74f5d24e9e65edf"
PV = "1.11.5+git${SRCPV}"
INCPR_append = ".0"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI += "git://git.gnome.org/cogl;protocol=git;branch=cogl-1.12"

S = "${WORKDIR}/git"

AUTOTOOLS_AUXDIR = "${S}/build"

