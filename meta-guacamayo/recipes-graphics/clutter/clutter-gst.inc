DESCRIPTION = "Clutter GStreamer"
HOMEPAGE = "http://www.clutter-project.org/"
LICENSE = "LGPLv2+"

INCPR = "r1"

PACKAGES  =+ "${PN}-examples"
PROVIDES  += "clutter-gst-1.10"
CONFLICTS += "clutter-gst-1.8"

STDDEPENDS     = "gst-plugins-base gst-plugins-bad"
STDDEPENDS_EGL = "clutter-1.10-egl"
STDDEPENDS_X11 = "clutter-1.10-x11"

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
