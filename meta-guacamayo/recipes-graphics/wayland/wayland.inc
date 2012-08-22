DESCRIPTION = "Wayland server and client"
HOMEPAGE = "http://wayland.freedesktop.org"
LICENSE = "MIT"
DEPENDS = "libffi"

LIC_FILES_CHKSUM = "file://COPYING;md5=1d4476a7d98dd5691c53d4d43a510c72"

INCPR = "r2"

SRC_URI = "http://wayland.freedesktop.org/releases/${BPN}-${PV}.tar.xz"

PACKAGES =+ "${PN}-server ${PN}-client ${PN}-cursor"

inherit autotools pkgconfig

# needs wayland-native for the wayland-scanner utility
BBCLASSEXTEND = "native"

DEPENDS_virtclass-native = "expat-native"
DEPENDS = "expat wayland-native"

EXTRA_OECONF_virtclass-native = "--disable-documentation"
EXTRA_OECONF = "--disable-documentation --disable-scanner"

# wayland installs a m4 macro for other projects to use. This m4 macro
# includes a path to a makefile fragment to get the rules to generate
# stubs from protocol description files.
do_install_append() {
  sed -i -e '4,10s,prefix=.*$,prefix=${D}/${prefix},;4,10s,exec_prefix=,exec_prefix=${D}/${exec_prefix},' \
    ${D}/${datadir}/aclocal/wayland-scanner.m4
}

EXTRA_OECONF += "--disable-scanner"

FILES_${PN}-server = "${bindir} ${libdir}/libwayland-server.so.*"

FILES_${PN}-client = "${libdir}/libwayland-client.so.*"
RDEPENDS_${PN}-client += "${PN}-server"

FILES_${PN}-cursor = "${libdir}/libwayland-cursor.so.*"
RDEPENDS_${PN}-cursor += "${PN}-client"
