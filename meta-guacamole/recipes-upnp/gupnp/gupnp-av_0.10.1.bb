SUMMARY = "Helpers for AV applications using UPnP"
DESCRIPTION = "GUPnP-AV is a collection of helpers for building AV (audio/video) applications using GUPnP."
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7 \
                    file://libgupnp-av/gupnp-av.h;beginline=1;endline=22;md5=d344132a8766641fcb11213ff5982086"
DEPENDS = "gupnp"

PR = "r1"

inherit autotools pkgconfig gnome

SRC_URI = "${GNOME_MIRROR}/${BPN}/${@gnome_verdir("${PV}")}/${BPN}-${PV}.tar.xz;name=archive"

SRC_URI[archive.md5sum] = "1eb8fa404f7b69189f94de3152ca5c2e"
SRC_URI[archive.sha256sum] = "41aeb5d243426b293c315788b41bc79a2be1f466eeff114cf6c89a37140f507e"
