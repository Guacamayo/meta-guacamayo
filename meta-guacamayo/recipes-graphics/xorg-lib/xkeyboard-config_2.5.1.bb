SUMMARY = "Keyboard configuration database for X Window"

DESCRIPTION = "The non-arch keyboard configuration database for X \
Window.  The goal is to provide the consistent, well-structured, \
frequently released open source of X keyboard configuration data for X \
Window System implementations.  The project is targeted to XKB-based \
systems."

HOMEPAGE = "http://freedesktop.org/wiki/Software/XKeyboardConfig"
BUGTRACKER = "https://bugs.freedesktop.org/enter_bug.cgi?product=xkeyboard-config"

LICENSE = "MIT & MIT-style"
LIC_FILES_CHKSUM = "file://COPYING;md5=0e7f21ca7db975c63467d2e7624a12f9"

SRC_URI = "http://xorg.freedesktop.org/archive/individual/data/xkeyboard-config-${PV}.tar.bz2"
SRC_URI[md5sum] = "29cbf3980bbe94c3ffc9c233ea638059"
SRC_URI[sha256sum] = "eb80e8dc38c389728bfca1f041af7658e7dd67faca8c763de02d5014ffd2cc92"

SECTION = "x11/libs"
DEPENDS = "intltool-native xkbcomp-native glib-2.0"

PR = "r3"

EXTRA_OECONF = "--with-xkb-rules-symlink=evdev --disable-runtime-deps"

FILES_${PN} += "${datadir}/X11/xkb"

inherit autotools pkgconfig

do_install_append () {
    install -d ${D}/usr/share/X11/xkb/compiled
    cd ${D}${datadir}/X11/xkb/rules && ln -sf base evdev
}
