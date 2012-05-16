DESCRIPTION="X11 session"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

RDEPENDS_${PN} += "${PN}-x11"
CONFLICTS_${PN} += "guacamayo-session-headless"

PR = "r1"

inherit useradd

SRC_URI = "file://etc"

ALLOW_EMPTY = "1"
PACKAGES =+ "${PN}-x11"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--home-dir=/var/run/rygel \
		       --create-home \
                       --groups video,audio \
                       --user-group rygel"

do_install_append() {
	cp -R ${WORKDIR}/etc ${D}/${sysconfdir}
	chmod -R 755 ${D}/${sysconfdir}
}

FILES_${PN} = ""
FILES_${PN}-x11 += "${sysconfdir}"
RDEPENDS_${PN}-x11 = "dbus-x11 xmodmap xdpyinfo xinit formfactor"
