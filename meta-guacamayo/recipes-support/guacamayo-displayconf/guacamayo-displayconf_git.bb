DESCRIPTION = "X11 display autoconfiguration utility"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"
DEPENDS = "glib-2.0 libxrandr"
HOMEPAGE = "http://github.com/Guacamayo/"

PR = "r1"
PV = "0.0+git${SRCPV}"

PACKAGES =+ "${PN}-autostart"
RDEPENDS_${PN}-autostart += "${PN}"

inherit autotools pkgconfig

SRC_URI = "git://github.com/Guacamayo/guacamayo-displayconf.git;branch=master;protocol=git \
          "
S = "${WORKDIR}/git"

do_install_append() {
    install -d ${D}/${sysconfdir}/xdg/autostart/
    cp ${D}/${datadir}/applications/*.desktop ${D}/${sysconfdir}/xdg/autostart/
}

FILES_${PN}-autostart = "${sysconfdir}/xdg/autostart"
