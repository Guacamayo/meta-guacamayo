DESCRIPTION = "librest is a helper library for RESTful services, using libsoup to provide HTTP communication."

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=2d5025d4aa3495befef8f17206a5b0a1 \
                    file://rest/rest-main.c;beginline=8;endline=19;md5=9f91b454290dc0fd7d6da2ed85cef107"

DEPENDS = "glib-2.0 libsoup-2.4"

inherit gnome gettext vala

S = "${WORKDIR}/rest-${PV}"

SRC_URI = "http://download.gnome.org/sources/rest/0.7/rest-${PV}.tar.xz"
SRC_URI[md5sum] = "dc14e0d89d38af5d8d544ce8f124d186"
SRC_URI[sha256sum] = "16ffa4929078dabfcfac31f9ce942072ebab67e3339ce9f378127457b03baf3a"

PR = "r1"

