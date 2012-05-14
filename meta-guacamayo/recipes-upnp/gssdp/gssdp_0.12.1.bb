SUMMARY = "Resource discovery and announcement over SSDP"
DESCRIPTION = "GSSDP implements resource discovery and announcement over SSDP (Simpe Service Discovery Protocol)."
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7"
DEPENDS = "glib-2.0 libsoup-2.4 libglade gobject-introspection-native"

SRC_URI[archive.md5sum] = "e7ac04abe7349a89d13d0ab4ee7d2e1b"
SRC_URI[archive.sha256sum] = "16acb4cc5249836ebe0e1758b75b85b8d529b51f72d9f68d8e4ae524d55f3347"

inherit autotools pkgconfig gnome

SRC_URI = "${GNOME_MIRROR}/${BPN}/${@gnome_verdir("${PV}")}/${BPN}-${PV}.tar.xz;name=archive"

PACKAGES =+ "gssdp-tools"

FILES_gssdp-tools = "${bindir}/gssdp* ${datadir}/gssdp/*.glade"

EXTRA_OECONF = "--disable-introspection"

PR = "r1"

