FILESEXTRAPATHS_prepend := "${THISDIR/${PN}:"

PRINC = "1"

# This exists in Yocto 1.1, but include it here for completeness
LIC_FILES_CHKSUM += "file://LICENCE.iwlwifi_firmware;md5=311cc823df5b1be4f00fbf0f17d96a6b"

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
