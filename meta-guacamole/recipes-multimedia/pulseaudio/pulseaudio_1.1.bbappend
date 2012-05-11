FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PRINC = "1"

SRC_URI += "file://pulse.conf"

do_install_append() {
    install -d ${D}${datadir}/alsa/alsa.conf.d
    install -m 0755 ${WORKDIR}/pulse.conf ${D}${datadir}/alsa/alsa.conf.d/

}

FILES_${PN} += "${datadir}/alsa"
