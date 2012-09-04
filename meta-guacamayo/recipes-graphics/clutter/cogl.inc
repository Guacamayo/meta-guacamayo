DESCRIPTION = "a modern 3D graphics API with associated utility APIs"
HOMEPAGE = "http://wiki.clutter-project.org/wiki/Cogl"
LICENSE = "LGPLv2.1+"

INCPR = "r1"

FILESPATH = "${FILE_DIRNAME}/cogl-1.10"

CONFLICTS += "cogl"
PROVIDES += "cogl-1.10"

PACKAGES =+ "${PN}-examples"

STDDEPENDS       = "gtk-doc-native pango glib-2.0 gdk-pixbuf"
STDDEPENDS_GL    = "virtual/libgl libdrm"
STDDEPENDS_EGL   = "virtual/egl"
STDDEPENDS_GLES2 = "virtual/libgles2"
STDDEPENDS_X11   = "virtual/libx11 libxcomposite libxfixes libxi"

# This has to be explictly listed, because cogl dlopens the backends
STDRDEPENDS       = ""
STDRDEPENDS_GL    = "libgl"
STDRDEPENDS_GLES2 = "libgles2"

SRC_URI = ""
SRC_URI_append_beagleboard += "file://beagleboard-glchar.patch"

# check for TARGET_FPU=soft and inform configure of the result so it can disable
# some floating points
require ${GUACABASE}/meta/recipes-graphics/clutter/clutter-fpu.inc

BASE_CONF = "--disable-gtk-doc			\
	     ${@get_clutter_fpu_setting(bb, d)}	\
	     --enable-examples-install	    	\
	     --enable-debug		    	\
	     --disable-gl			\
	     --disable-gles			\
	     --disable-gles2			\
	     --disable-glx			\
	    "

BASE_CONF_EGL_GL = "--enable-gl			\
		    --enable-kms-egl-platform	\
		   "

BASE_CONF_EGL_GLES = "--enable-gles2			\
		      --enable-null-egl-platform	\
		     "

BASE_CONF_X11_GLES = "--enable-gles2			\
		      --enable-xlib-egl-platform	\
		     "
BASE_CONF_X11_GL = "--enable-gl		\
		    --enable-glx	\
		   "

inherit autotools pkgconfig gtk-doc gettext

#Fix up some weirdness in the docs
do_configure_prepend() {
        sed -i s:doc/reference/Makefile::g ${S}/configure.ac
        sed -i s:doc::g ${S}/Makefile.am

	# Disable DOLT
	sed -i -e 's/^DOLT//' ${S}/configure.ac
}

FILES_${PN}-examples = "${bindir}/* ${datadir}/cogl/examples-data/*"

