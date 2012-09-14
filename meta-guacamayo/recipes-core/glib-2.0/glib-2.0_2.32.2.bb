require ${COREBASE}/meta/recipes-core/glib-2.0/glib.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://glib/glib.h;startline=4;endline=17;md5=a4332fe58b076f29d07c9c066d2967b6 \
                    file://gmodule/COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://gmodule/gmodule.h;startline=4;endline=17;md5=76ab161b37202cd004073c42fac276ed \
                    file://glib/pcre/COPYING;md5=266ebc3ff74ee9ce6fad65577667c0f4 \
                    file://glib/pcre/pcre.h;startline=11;endline=35;md5=2ffb79f0a0933f282f4f36cda635683d \
                    file://docs/reference/COPYING;md5=f51a5100c17af6bae00735cd791e1fcc"

PR = "r2"
PE = "1"

#DEFAULT_PREFERENCE="-1"

DEPENDS += "libffi python-argparse-native zlib"
DEPENDS_virtclass-native += "libffi-native python-argparse-native"
DEPENDS_virtclass-nativesdk += "libffi-nativesdk python-argparse-native zlib-nativesdk"

EXTRA_OECONF += "--enable-debug=minimal"

SHRT_VER = "${@d.getVar('PV',1).split('.')[0]}.${@d.getVar('PV',1).split('.')[1]}"

SRC_URI = "${GNOME_MIRROR}/glib/${SHRT_VER}/glib-${PV}.tar.xz \
           file://configure-libtool.patch \
           file://${COREBASE}/meta/recipes-core/glib-2.0/glib-2.0/60_wait-longer-for-threads-to-die.patch \
           file://${COREBASE}/meta/recipes-core/glib-2.0/glib-2.0/glib-2.0_fix_for_x32.patch \
           file://${COREBASE}/meta/recipes-core/glib-2.0/glib-2.0/nodbus.patch \
          "
SRC_URI[md5sum] = "5bfdb6197afb90e4dbc7b1bb98f0eae0"
SRC_URI[sha256sum] = "b1764abf00bac96e0e93e29fb9715ce75f3583579acac40648e18771d43d6136"

# Only apply this patch for target recipe on uclibc
SRC_URI_append_libc-uclibc = " ${@['', 'file://${COREBASE}/meta/recipes-core/glib-2.0/glib-2.0/no-iconv.patch']['${PN}' == '${BPN}']}"

SRC_URI_append_virtclass-native = " file://${COREBASE}/meta/recipes-core/glib-2.0/glib-2.0/glib-gettextize-dir.patch"
BBCLASSEXTEND = "native nativesdk"

do_configure_prepend() {
  # missing ${topdir}/gtk-doc.make and --disable-gtk-doc* is not enough, because it calls gtkdocize (not provided by gtk-doc-native)
  sed -i '/^docs/d' ${S}/configure.ac
  sed -i 's/SUBDIRS = . m4macros glib gmodule gthread gobject gio tests po docs/SUBDIRS = . m4macros glib gmodule gthread gobject gio tests po/g' ${S}/Makefile.am
  sed -i -e "s:TEST_PROGS += gdbus-serialization::g"  ${S}/gio/tests/Makefile.am
}

do_install_append() {
  # remove some unpackaged files
  rm -f ${D}${libdir}/gdbus-2.0/codegen/*.pyc
  rm -f ${D}${libdir}/gdbus-2.0/codegen/*.pyo
  # and empty dirs
  rmdir ${D}${libdir}/gio/modules/
  rmdir ${D}${libdir}/gio/
}

PACKAGES += "${PN}-codegen"
FILES_${PN}-codegen = "${libdir}/gdbus-2.0/codegen/*.py"
FILES_${PN} += "${datadir}/glib-2.0/gettext/mkinstalldirs ${datadir}/glib-2.0/gettext/po/Makefile.in.in"
