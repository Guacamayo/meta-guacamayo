FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-0.10.36:"

PRINC = "1"

EXTRA_OECONF += " --disable-ivorbis"

