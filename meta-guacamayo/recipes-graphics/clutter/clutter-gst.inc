DESCRIPTION = "Clutter GStreamer"
HOMEPAGE = "http://www.clutter-project.org/"
LICENSE = "LGPLv2+"

INCPR = "r1"

PACKAGES  =+ "${PN}-examples"
CONFLICTS += "clutter-gst-1.8 clutter-gst-1.10 clutter-gst-1.12"

DEPENDS     = "gst-plugins-base gst-plugins-bad clutter-1.12"
RDEPENDS_${PN} += "gst-meta-base"

FILESPATH = "${FILE_DIRNAME}/clutter-gst"

FILES_${PN}          += "${libdir}/gstreamer-0.10/lib*.so"
FILES_${PN}-dev      += "${libdir}/gstreamer-0.10/*.la"
FILES_${PN}-dbg      += "${libdir}/gstreamer-0.10/.debug/lib*.so"
FILES_${PN}-examples  = "${bindir}/video-player ${bindir}/video-sink"

inherit autotools pkgconfig gtk-doc

do_configure_prepend () {
       # Disable DOLT
       sed -i -e 's/^DOLT//' ${S}/configure.ac
}
