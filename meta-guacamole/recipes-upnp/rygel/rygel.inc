LICENSE = "LGPLv2+"

inherit gnome

SRC_URI = "${GNOME_MIRROR}/${BPN}/${@gnome_verdir("${PV}")}/${BPN}-${PV}.tar.xz;name=archive"

DESCRIPTION = "Collection of DLNA[1] (UPnP[2] AV) devices, implemented through a plug-in mechanism."
SECTION = "network/multimedia"
DEPENDS = "glib-2.0 gupnp gupnp-av gstreamer sqlite3 libsoup-2.4 libgee"
HOMEPAGE = "http://live.gnome.org/Rygel"

FILES_${PN} += "${libdir}/rygel-1.0/librygel*.so ${datadir}/dbus-1/"
FILES_${PN}-dbg += "${libdir}/rygel-1.0/.debug/"
