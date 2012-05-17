DESCRIPTION="X11 session"
# The session scripts come from x11-common, which is GPLv2
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

RDEPENDS_${PN} += "${PN}-x11"
CONFLICTS_${PN} += "guacamayo-session-headless"

PR = "r7"

inherit useradd

SRC_URI = "file://etc\
           file://gplv2-license.patch"

ALLOW_EMPTY = "1"
PACKAGES =+ "${PN}-x11"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--home-dir=/var/run/rygel \
		       --create-home \
                       --groups video,audio \
                       --user-group rygel"

do_install_append() {
        # remove back up files; these can completely screw up the session
	find ${WORKDIR}/etc -name "*~" -type f -print0 | xargs -0 /bin/rm -f
	cp -R ${WORKDIR}/etc ${D}/${sysconfdir}
	chmod -R 755 ${D}/${sysconfdir}
}

FILES_${PN} = ""
FILES_${PN}-x11 += "${sysconfdir}"
RDEPENDS_${PN}-x11 = "dbus-x11 xmodmap xdpyinfo xinit formfactor"
