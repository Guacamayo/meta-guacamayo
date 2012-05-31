FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC = "2"

inherit update-rc.d

SRC_URI += "file://rpi-zram-service.initd"

do_install_append () {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/${PN}.initd ${D}${sysconfdir}/init.d/${PN}
}

FILES_{PN} = "${bindir}"
PACKAGES =+ "${PN}-systemd ${PN}-initd"

SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE_${PN}-system = "rpi-zram.service"
FILES_${PN}-systemd += "${base_libdir}/systemd"
RDEPENDS_${PN}-systemd += "${PN}"

INITSCRIPT_PACKAGES = "${PN}-initd"
INITSCRIPT_NAME_${PN}-initd = "${PN}"
INITSCRIPT_PARAMS_${PN}-initd = "start 03 5 4 3 2 . stop 90 0 1 6 ."
FILES_${PN}-initd += "${sysconfdir}/init.d"
RDEPENDS_${PN}-initd += "${PN}"
