FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-0.10.31:"

PRINC = "1"

EXTRA_OECONF_append_raspberrypi = " --disable-videomixer"

