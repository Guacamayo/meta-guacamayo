SUMMARY = "Vala bindings for gupnpn"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7"
DEPENDS = "gupnp gupnp-av gupnp-dlna gssdp"

INC_PR = "r1"

EXTRA_OECONF = "--disable-introspection"

inherit autotools pkgconfig gnome vala

SRC_URI = "${GNOME_MIRROR}/${BPN}/${@gnome_verdir("${PV}")}/${BPN}-${PV}.tar.xz;name=archive"


