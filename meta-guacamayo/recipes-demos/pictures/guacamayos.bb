
SUMMARY = "Guacamayos"
DESCRIPTION = "Pictures of Guacamayo"
LICENSE = "CC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3c1fcfb974466dcdec8ae4b28753aa2"

FILESEXTRAPATHS_prepend := "${THISDIR}/guacamayos:"

SRC_URI = "file://LICENSE \
	   file://guacamayos.tar.bz2"

inherit allarch

do_configure_prepend () {
    cp ${WORKDIR}/LICENSE ${S}
}

do_install() {
    install -d ${D}${datadir}/demos/pictures/
    install -m 0644 ${WORKDIR}/*.jpg ${D}${datadir}/demos/pictures/
    install -m 0644 ${WORKDIR}/LICENSE ${D}${datadir}/demos/pictures/LICENSE.guacamayos
}

FILES_${PN} += "${datadir}"
