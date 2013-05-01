DESCRIPTION="Audioplayer session"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${THISDIR}/../../COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

RDEPENDS_${PN} += "sudo pulseaudio-server \
		   ${PN}-initd		  \
		   guacamayo-cli	  \
		   guacamayo-watchdog"

CONFLICTS_${PN} += "guacamayo-session-x11"

PR = "r3"

inherit update-rc.d useradd

SRC_URI = "file://guacamayo-session-audioplayer	\
	   file://guacamayo-session-common	\
	  "

ALLOW_EMPTY_${PN} = "1"
PACKAGES =+ "${PN}-initd"

INITSCRIPT_PACKAGES = "${PN}-initd"
INITSCRIPT_NAME_${PN}-initd = "${PN}"

# need dbus, so start after and stop before
INITSCRIPT_PARAMS_${PN}-initd = "start 50 5 2 3 . stop 10 0 1 6 ."

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--home-dir=/home/rygel \
		       --create-home \
                       --groups video,audio \
                       --user-group rygel"

do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/${PN} ${D}${sysconfdir}/init.d
    install -m 0644 ${WORKDIR}/guacamayo-session-common ${D}/${sysconfdir}
}

FILES_${PN}-initd += "${sysconfdir}/init.d"
