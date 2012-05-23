DESCRIPTION="Headless session"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

RDEPENDS_${PN} += "sudo pulseaudio-server ${PN}-initd"
CONFLICTS_${PN} += "guacamayo-session-x11 guacamayo-session-headless"

PR = "r1"

inherit update-rc.d useradd

SRC_URI = "file://guacamayo-session-egl"

ALLOW_EMPTY = "1"
PACKAGES =+ "${PN}-initd"

INITSCRIPT_PACKAGES = "${PN}-initd"
INITSCRIPT_NAME_${PN}-initd = "${PN}"

# need dbus, so start after and stop before
INITSCRIPT_PARAMS_${PN}-initd = "start 50 5 2 3 . stop 10 0 1 6 ."

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--home-dir=/var/run/rygel \
		       --create-home \
                       --groups video,audio \
                       --user-group rygel"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/${PN} ${D}${sysconfdir}/init.d
}

FILES_${PN}-initd += "${sysconfdir}/init.d"
