DESCRIPTION = "Command line shell for Guacamayo"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ee31012bf90e7b8c108c69f197f3e3a4"
DEPENDS = "glib-2.0 guacamayo-headers readline"
HOMEPAGE = "http://guacamayo-project.org"

PR = "r0"
PV = "0.0+git${SRCPV}"

inherit autotools pkgconfig

SRC_URI = "git://github.com/Guacamayo/guacamayo-cli.git;branch=master;protocol=git"
S = "${WORKDIR}/git"


