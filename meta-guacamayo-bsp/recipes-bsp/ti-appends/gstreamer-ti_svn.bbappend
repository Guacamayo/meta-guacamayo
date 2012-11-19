FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC = "3"

FILES_${PN}-staticdev += "${libdir}/gstreamer-0.10/*.a"
