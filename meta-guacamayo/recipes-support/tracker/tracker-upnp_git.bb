DESCRIPTION = "Tracker Upnp"
LICENSE = "LGPL2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=f0df8fd67dfa1db3cc0bd431837f0b89"
DEPENDS = "vala-native glib-2.0 tracker gupnp-vala"
HOMEPAGE = "http://gitorious.org/tracker-upnp"

SRCREV = "7bfee9cadfdc06a53fe5921b6abf50c4e09948e0"
PR = "r2"
PV = "0.1.5+git${SRCPV}"

inherit autotools pkgconfig vala

SRC_URI = "git://gitorious.org/tracker-upnp/tracker-upnp.git;proto=git \
	   file://tracker-0.12.patch \
	   file://rm-init-timeout-on-connect.patch \
	  "

S = "${WORKDIR}/git"

FILES_${PN} += "${datadir}/dbus-1 ${datadir}/tracker"
