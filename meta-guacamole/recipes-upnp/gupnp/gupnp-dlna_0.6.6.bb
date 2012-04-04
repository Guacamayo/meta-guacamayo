SUMMARY = "Helpers for AV applications using UPnP"
DESCRIPTION = "GUPnP DLNA is a small utility library that aims to ease the DLNA-related tasks such as media profile guessing, transcoding to a given profile, etc."
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    "
DEPENDS = "gupnp"

PR = "r1"

inherit autotools pkgconfig gnome

SRC_URI = "${GNOME_MIRROR}/${BPN}/${@gnome_verdir("${PV}")}/${BPN}-${PV}.tar.xz;name=archive"

SRC_URI[archive.md5sum] = "4e3151125de991f474f728c1c5343ab1"
SRC_URI[archive.sha256sum] = "82a1e75c398379567a5a5db9acd19bd01334b4f0053c8a166a77bc09bf0ca047"
