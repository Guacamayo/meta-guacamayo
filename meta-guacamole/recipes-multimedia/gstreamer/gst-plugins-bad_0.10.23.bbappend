FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-0.10.23:"

PRINC = "1"

DEPENDS += "libid3tag"

EXTRA_OECONF += "--with-plugins=id3tag"

