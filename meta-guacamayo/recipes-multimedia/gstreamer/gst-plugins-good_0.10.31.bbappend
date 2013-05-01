FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-0.10.31:"

# Depends from upstream with gtk+ removed
DEPENDS = "gstreamer gst-plugins-base gconf cairo jpeg libpng zlib libid3tag flac \
	   speex libsoup-2.4 pulseaudio"

PRINC = "3"
DEPENDS += "orc orc-native"

EXTRA_OECONF += "--enable-orc --disable-examples"
EXTRA_OECONF_append_raspberrypi = " --disable-videomixer"

python populate_packages_prepend () {
    pn = d.getVar('PN', True)
    metapkg = pn + '-glib'
    d.setVar('ALLOW_EMPTY_' + metapkg, "1")
    d.setVar('FILES_' + metapkg, "")

    metapkg = pn + '-apps'
    d.setVar('ALLOW_EMPTY_' + metapkg, "1")
    d.setVar('FILES_' + metapkg, "")
} 

