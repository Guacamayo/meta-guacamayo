FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

RDEPENDS += "libgles-omap3-driwsegl"

SRC_URI += "file://powervr.ini"

PRINC = "1"

do_install_append() {
    cp ${WORKDIR}/powervr.ini ${D}${sysconfdir}/
}
