FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-0.10.36:"

PRINC = "2"
DEPENDS += "orc orc-native"

EXTRA_OECONF += " --disable-ivorbis --enable-orc"

