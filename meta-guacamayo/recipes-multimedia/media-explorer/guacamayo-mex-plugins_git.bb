DESCRIPTION = "Networks plugin for mex"
SECTION = "x11/multimedia"
LICENSE = "LGPLv2.1"

DEPENDS = "media-explorer guacamayo-headers"

inherit autotools

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    "
FILES_${PN} += "${libdir}/media-explorer/plugins/*.so"
FILES_${PN}-dbg += "${libdir}/media-explorer/plugins/.debug/*.so"

PV = "0.1.0+git${SRCPV}"
SRC_URI = "git://github.com/Guacamayo/guacamayo-mex-plugins.git;proto=git"

S = "${WORKDIR}/git"

PR = "r3"

