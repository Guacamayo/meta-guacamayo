require ${COREBASE}/meta/recipes-graphics/xorg-lib/xorg-lib-common.inc

SUMMARY = "library interface to the XKB compiler"

LIC_FILES_CHKSUM = "file://COPYING;md5=469a86f811ec5d6bba592811c3005a2a"
PR = "r2"
DEPENDS = "xproto kbproto ${PN}-native xkeyboard-config"
DEPENDS_virtclass-native = "xproto kbproto"

S = "${WORKDIR}/git"

patches = "file://cross.patch"
patches_virtclass-native = ""
SRC_URI = "git://anongit.freedesktop.org/xorg/lib/libxkbcommon ${patches}"
SRCREV = "3d672fcfea6b823db4793b9ad1c3aadc4b547a08"

BBCLASSEXTEND = "native"

do_install_append_virtclass-native() {
	install -m 755 "makekeys/makekeys" "${STAGING_BINDIR}/libxkbcommon-makekeys"
}
