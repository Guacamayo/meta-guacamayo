FILESEXTRAPATHS_prepend := "${THISDIR}/guacamayo:"

PRINC = "2"

SRC_URI  += "file://fix-storage-dir.patch \
	     file://settings		  \
	    "

do_install_append() {
	install -d ${D}${sysconfdir}/connman
	install -m 0755 ${WORKDIR}/settings ${D}${sysconfdir}/connman/
}

PACKAGES =+ "${PN}-guacamayo-ntp"

FILES_${PN}-guacamayo-ntp         = "${sysconfdir}/connman/settings"
RDEPENDS_${PN}-guacamayo-ntp      = "${PN}"
LICENSE_FLAGS_${PN}-guacamayo-ntp = "non-commercial"
