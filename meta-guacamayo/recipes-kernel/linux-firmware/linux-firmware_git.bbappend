FILESEXTRAPATHS_prepend := "${THISDIR/${PN}:"

PRINC = "1"

FWPATH = "/lib/firmware"

do_install_append() {
	install -m 0644 LICENCE.iwlwifi_firmware ${D}${FWPATH}
	install -m 0644 iwlwifi-*.ucode ${D}${FWPATH}
}

PACKAGES =+ "${PN}-iwlwifi"

FILES_${PN}-iwlwifi = "  \
  ${FWPATH}/LICENCE.iwlwifi_firmware \
  ${FWPATH}/iwlwifi-*.ucode  \
"
