DESCRIPTION = "Clutter graphics library"
HOMEPAGE = "http://www.clutter-project.org/"
LICENSE = "LGPLv2.1+"

INCPR = "r0"

# check for TARGET_FPU=soft and inform configure of the result so it can disable
# some floating points
require ${GUACABASE}/meta/recipes-graphics/clutter/clutter-fpu.inc

STDDEPENDS = "gtk-doc-native pango glib-2.0 libxfixes json-glib atk \
	      udev libxkbcommon cogl-1.12"

STDDEPENDS_GLX = "virtual/libx11 libxi virtual/libgl"
STDDEPENDS_EGL = "virtual/egl"

STDRDEPENDS     = "xkeyboard-config"
STDRDEPENDS_EGL = ""
STDRDEPENDS_GLX = ""

CONFLICTS += "clutter-1.8 clutter-1.10"

S = "${WORKDIR}/git"
AUTOTOOLS_AUXDIR = "${S}/build"

# Disable pretty much everything, override in platform specific set up
BASE_CONF = "--disable-gtk-doc			\
	     ${@get_clutter_fpu_setting(bb, d)}	\
             --disable-introspection	    	\
	     --disable-egl-backend		\
	     --disable-quartz-backend		\
	     --disable-win32-backend		\
	     --disable-x11-backend		\
	     --disable-gdk-backend		\
	     --disable-wayland-backend		\
	     --disable-wayland-compositor	\
	     --disable-cex100-backend		\
	     --disable-tslib-input		\
	     --disable-evdev-input		\
	    "

BASE_CONF_EGL = "--enable-egl-backend		\
		 --enable-evdev-input		\
		"

BASE_CONF_GLX = "--enable-x11-backend		\
		 --enable-evdev-input		\
		"

PACKAGES =+ "${PN}-examples"

inherit autotools pkgconfig gtk-doc gettext

FILES_${PN}-examples = "${bindir}/test-* ${pkgdatadir}/redhand.png"

do_configure_prepend() {
        # Fix up some weirdness in the docs
        sed -i s:doc/reference/Makefile::g ${S}/configure.ac
        sed -i s:doc::g ${S}/Makefile.am

	# see https://bugzilla.gnome.org/show_bug.cgi?id=661128 for this
	touch -t 200001010000 po/clutter-1.0.pot

	# Disable DOLT
	sed -i -e 's/^DOLT//' ${S}/configure.ac
}
