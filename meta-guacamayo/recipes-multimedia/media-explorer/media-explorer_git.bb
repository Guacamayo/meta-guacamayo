
include media-explorer-0.5.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://mex/mex-main.c;beginline=6;endline=16;md5=5bc999bfc01d0dd6e29e336c7dd2f7d1"


SRCREV = "22f7b71747c1e2f958d2acffb2ca6b8d49039c6c"
PV = "0.5.0+git${SRCPV}"

SRC_URI = "git://github.com/media-explorer/media-explorer.git;proto=git \
	   file://fix-thumbnailer.patch \
	  "

S = "${WORKDIR}/git"

PR = "${INCPR}.2"

