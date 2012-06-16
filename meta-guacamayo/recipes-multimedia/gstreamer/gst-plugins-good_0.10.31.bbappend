FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-0.10.31:"

PRINC = "2"
DEPENDS += "orc orc-native"

EXTRA_OECONF += "--enable-orc"
EXTRA_OECONF_append_raspberrypi = " --disable-videomixer"

