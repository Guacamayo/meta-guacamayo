SUMMARY = "Media discovery framework"
DESCRIPTION = "Grilo is a framework focused on making media discovery and browsing easy for application developers."
HOMEPAGE = "http://live.gnome.org/Grilo"

LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

DEPENDS = "glib-2.0 libxml2 gtk+ gconf libsoup-2.4 grilo gupnp gupnp-av sqlite3 libgcrypt"

SRC_URI[archive.md5sum] = "7bea4ea6b58c345ffa9ded177b917ff3"
SRC_URI[archive.sha256sum] = "7e382f402119f4f270380627a2f49b30a6c43a47ecd645bf5ffe4e0cd99a1c79"

PR = "r0"

inherit gnome

# gnomebase.bbclass assumes .bz2 rather than .xz, so we have to set this ourselves
SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/${BPN}/0.1/${BPN}-${PV}.tar.xz;name=archive \
           file://0001-upnp-poll-for-presence-of-upnp-media-server.patch"

FILES_${PN} += " ${libdir}/grilo-0.1/*.so ${libdir}/grilo-0.1/grl-*.xml"
FILES_${PN}-dev += " ${libdir}/grilo-0.1/*.la ${libdir}/grilo-0.1/*.a"
FILES_${PN}-dbg += " ${libdir}/grilo-0.1/.debug/"
