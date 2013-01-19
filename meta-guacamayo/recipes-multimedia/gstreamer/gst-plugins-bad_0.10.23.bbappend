FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-0.10.23:"

PRINC = "3"

DEPENDS += "libid3tag orc orc-native"

# Note: the --with-plugins option is not cumulative, only what is selected
#       here will be built
EXTRA_OECONF += "--with-plugins=musicbrainz,wavpack,ivorbis,videoparsers,id3tag --enable-orc"

