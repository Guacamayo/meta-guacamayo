FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-0.10.23:"

PRINC = "2"

DEPENDS += "libid3tag orc orc-native"

EXTRA_OECONF += "--with-plugins=id3tag --enable-orc"

