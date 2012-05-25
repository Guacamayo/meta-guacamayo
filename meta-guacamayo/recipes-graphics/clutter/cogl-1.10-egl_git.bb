require ${GUACABASE}/meta/recipes-graphics/clutter/cogl.inc

FILESPATH = "${FILE_DIRNAME}/cogl-1.10"

STDDEPENDS = "gtk-doc-native pango glib-2.0 gdk-pixbuf"

DEPENDS = "${STDDEPENDS} virtual/egl virtual/libgl"
DEPENDS_beagleboard = "${STDDEPENDS} virtual/egl"
DEPENDS_append_atom-pc += " libdrm"

CONFLICTS = "cogl"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

# the 1.10.2 tag
SRCREV = "ebb8cdd7c6c6c5d6c6ff92e40aacb0a78e6abdbc"
PV = "1.10.2+git${SRCPV}"
PR = "r8"

DEFAULT_PREFERENCE = "1"

PACKAGES =+ "${PN}-examples"

SRC_URI = "git://git.gnome.org/cogl;protocol=git;branch=master"
SRC_URI_append_beagleboard += "file://beagleboard-glchar.patch"

S = "${WORKDIR}/git"

AUTOTOOLS_AUXDIR = "${S}/build"

# standard config EGL with big GL
EXTRA_OECONF = " ${BASE_CONF} --disable-gles2 --enable-gl --disable-glx --enable-kms-egl-platform --enable-examples-install --enable-debug"

EXTRA_OECONF_beagleboard = " ${BASE_CONF} --enable-gles2 --disable-gl --disable-glx --enable-null-egl-platform --enable-examples-install --enable-debug"


FILES_${PN}-examples = "${bindir}/* ${datadir}/cogl/examples-data/*"

do_configure_prepend () {
	# Disable DOLT
	sed -i -e 's/^DOLT//' ${S}/configure.ac
}
