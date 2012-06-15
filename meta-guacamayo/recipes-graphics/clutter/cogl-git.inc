# the 1.10.2 tag
SRCREV = "ebb8cdd7c6c6c5d6c6ff92e40aacb0a78e6abdbc"
PV = "1.10.2+git${SRCPV}"
PR = "${INCPR}.2"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

SRC_URI += "git://git.gnome.org/cogl;protocol=git;branch=master"

S = "${WORKDIR}/git"

AUTOTOOLS_AUXDIR = "${S}/build"

