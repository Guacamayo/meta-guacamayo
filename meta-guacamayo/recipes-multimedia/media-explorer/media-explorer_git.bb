
include media-explorer.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://mex/mex-main.c;beginline=6;endline=16;md5=5bc999bfc01d0dd6e29e336c7dd2f7d1"


SRCREV = "9d70bba60fedfd29c321d6bbb9c8309dbdefa976"
PV = "0.5.0+git${SRCPV}"

SRC_URI = "git://github.com/media-explorer/media-explorer.git;proto=git"

S = "${WORKDIR}/git"

PR = "${INCPR}.1"

