DESCRIPTION = "Simple process watchdog"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ee31012bf90e7b8c108c69f197f3e3a4"
DEPENDS = "glib-2.0"
HOMEPAGE = "http://github.com/Guacamayo/"

PR = "r1"
PV = "0.0+git${SRCPV}"

PACKAGES =+ "${PN}-autostart"
RDEPENDS_${PN}-autostart += "${PN}"

inherit autotools pkgconfig

SRC_URI = "git://github.com/Guacamayo/guacamayo-watchdog.git;branch=master;proto=git \
	   file://etc \
          "
S = "${WORKDIR}/git"

do_install_append() {
        # remove back up files; these can completely screw up the session
	find ${WORKDIR}/etc -name "*~" -type f -print0 | xargs -0 /bin/rm -f
	cp -R ${WORKDIR}/etc ${D}/${sysconfdir}
	chmod -R 755 ${D}/${sysconfdir}
}

FILES_${PN}-autostart = "${sysconfdir}/xdg/autostart"
