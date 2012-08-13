DESCRIPTION = "Tracker Upnp"
LICENSE = "LGPL2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=f0df8fd67dfa1db3cc0bd431837f0b89"
DEPENDS = "vala-native glib-2.0 tracker gupnp-vala"
HOMEPAGE = "http://gitorious.org/tracker-upnp"

SRCREV = "685ab29c82809d77c2fddbf08ebd10d5f8274399"
PR = "r4"
PV = "0.1.5+git${SRCPV}"

inherit autotools pkgconfig vala

SRC_URI = "git://git.gnome.org/tracker-upnp;proto=git \
	   file://tracker-0.14.patch \
	  "

S = "${WORKDIR}/git"

FILES_${PN} += "${datadir}/dbus-1 ${datadir}/tracker"
