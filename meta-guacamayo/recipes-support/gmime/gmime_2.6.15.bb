LICENSE = "LGPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"
DESCRIPTION = "Runtime libraries for parsing and creating MIME mail"
SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "glib-2.0 zlib gpgme"

inherit gnome autotools lib_package binconfig

GNOME_COMPRESS_TYPE="xz"

SRC_URI += "file://iconv-detect.h   \
            file://nodolt.patch \
            "

EXTRA_OECONF += "--disable-mono"

export ac_cv_have_iconv_detect_h="yes"
do_configure_append = "cp ${WORKDIR}/iconv-detect.h ${S}"

# we do not need GNOME 1 gnome-config support
do_install_append () {
	rm -f ${D}${libdir}/gmimeConf.sh
}

SRC_URI[archive.md5sum] = "a139ee5870ec4c0bf28fcff8ac0af444"
SRC_URI[archive.sha256sum] = "b4c2a0b99b82063387cd750a38421ebaa0636f339e67984a84371bcb697dc99a"

