DESCRIPTION = "libgee is a collection library providing GObject-based interfaces \
and classes for commonly used data structures."
HOMEPAGE = "http://live.gnome.org/Libgee"
SECTION = "libs"
DEPENDS = "glib-2.0"
BBCLASSEXTEND = "native"
LICENSE = "LGPLv2+"
INC_PR = "r8"
PE = "1"

inherit autotools pkgconfig gnome vala

SRC_URI = "${GNOME_MIRROR}/${BPN}/${@gnome_verdir("${PV}")}/${BPN}-${PV}.tar.xz;name=archive"

do_configure_prepend() {
     MACROS="libtool.m4 lt~obsolete.m4 ltoptions.m4 ltsugar.m4 ltversion.m4"
      for i in ${MACROS}; do
         rm -f m4/$i
      done
}
