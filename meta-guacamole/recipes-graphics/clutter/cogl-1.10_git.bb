require ${GUACABASE}/meta/recipes-graphics/clutter/cogl.inc

FILESPATH = "${FILE_DIRNAME}/cogl-1.10"

DEPENDS += "virtual/egl"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

# the 1.10.2 tag
SRCREV = "5ad99f6c3e56d13574b6904c0e625dff2c24f075"
PV = "1.10.2+git${SRCPV}"
PR = "r5"

DEFAULT_PREFERENCE = "1"

PACKAGES =+ "${PN}-examples"

SRC_URI = "git://git.gnome.org/cogl;protocol=git;branch=master"
SRC_URI_append_beagleboard += "file://beagleboard-glchar.patch"

S = "${WORKDIR}/git"

AUTOTOOLS_AUXDIR = "${S}/build"

EXTRA_OECONF = "${BASE_CONF} --enable-gles2 --disable-gl --disable-glx --enable-examples-install"

EXTRA_OECONF_beagleboard = " ${BASE_CONF} --enable-gles2 --disable-gl --disable-glx --enable-null-egl-platform --enable-examples-install"

FILES_${PN}-examples = "${bindir}/* ${datadir}/cogl/examples-data/*"

do_configure_prepend () {
	# Disable DOLT
	sed -i -e 's/^DOLT//' ${S}/configure.ac
}
