LICENSE = "LGPLv2+"

INC_PR = "r3"

inherit gnome vala

SRC_URI = "${GNOME_MIRROR}/${BPN}/${@gnome_verdir("${PV}")}/${BPN}-${PV}.tar.xz;name=archive"

SRC_URI += "file://config.patch"

DESCRIPTION = "Collection of DLNA[1] (UPnP[2] AV) devices, implemented through a plug-in mechanism."
SECTION = "network/multimedia"
DEPENDS = "glib-2.0 gupnp gupnp-av gupnp-dlna gstreamer gst-plugins-base sqlite3 libsoup-2.4 libgee shared-mime-info tracker"
RDEPENDS_${PN} = "sudo shared-mime-info-data pulseaudio-server"

require rygel-plugins.inc
PACKAGES_DYNAMIC = "${PN}-*"

HOMEPAGE = "http://live.gnome.org/Rygel"

FILES_${PN} += "${datadir}/dbus-1/"
FILES_${PN}-dbg += "${libdir}/rygel-1.0/.debug/"
