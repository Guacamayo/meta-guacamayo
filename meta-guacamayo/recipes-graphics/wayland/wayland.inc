DESCRIPTION = "Wayland server and client"
HOMEPAGE = "http://wayland.freedesktop.org"
LICENSE = "MIT"
DEPENDS = "libffi"

LIC_FILES_CHKSUM = "file://COPYING;md5=1d4476a7d98dd5691c53d4d43a510c72"

INCPR = "r1"

SRC_URI = "http://wayland.freedesktop.org/releases/${BPN}-${PV}.tar.xz"

PACKAGES =+ "${PN}-server ${PN}-client ${PN}-cursor"

inherit autotools

EXTRA_OECONF += "--disable-scanner"

FILES_${PN}-server = "${bindir} ${libdir}/libwayland-server.so.*"

FILES_${PN}-client = "${libdir}/libwayland-client.so.*"
RDEPENDS_${PN}-client += "${PN}-server"

FILES_${PN}-cursor = "${libdir}/libwayland-cursor.so.*"
RDEPENDS_${PN}-cursor += "${PN}-client"
