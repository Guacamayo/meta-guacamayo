require ${GUACABASE}/meta/recipes-graphics/clutter/clutter.inc

STDDEPENDS = "virtual/libx11 gtk-doc-native pango glib-2.0 libxfixes libxi json-glib cogl-1.10-x11 atk udev libxkbcommon"
CONFLICTS = "clutter-1.8 clutter-1.10-egl"
PROVIDES += "clutter-1.10"
RDEPENDS = "xkeyboard-config"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

# the 1.10.2 tag
SRCREV = "62cffec9036f6a24736dc47914844fe9336c8ce4"
PV = "1.10.2+git${SRCPV}"
PR = "r0"

PACKAGES =+ "${PN}-examples"

DEFAULT_PREFERENCE = "1"

SRC_URI = "git://git.gnome.org/clutter;protocol=git;branch=master \
           file://${GUACABASE}/meta/recipes-graphics/clutter/clutter/enable_tests-654c26a1301c9bc5f8e3e5e3b68af5eb1b2e0673.patch;rev=654c26a1301c9bc5f8e3e5e3b68af5eb1b2e0673 \
           file://${GUACABASE}/meta/recipes-graphics/clutter/clutter/enable_tests.patch;notrev=654c26a1301c9bc5f8e3e5e3b68af5eb1b2e0673 "

S = "${WORKDIR}/git"

BASE_CONF += "--disable-introspection"

EXTRA_OECONF = "${BASE_CONF} --disable-egl-backend --disable-quartz-backend --disable-win32-backend --enable-x11-backend --disable-gdk-backend --disable-wayland-backend --disable-wayland-compositor --disable-cex100-backend --disable-tslib-input --enable-evdev-input"

AUTOTOOLS_AUXDIR = "${S}/build"

FILES_${PN}-examples = "${bindir}/test-* ${pkgdatadir}/redhand.png"

do_configure_prepend () {
	# see https://bugzilla.gnome.org/show_bug.cgi?id=661128 for this
	touch -t 200001010000 po/clutter-1.0.pot

	# Disable DOLT
	sed -i -e 's/^DOLT//' ${S}/configure.ac
}

