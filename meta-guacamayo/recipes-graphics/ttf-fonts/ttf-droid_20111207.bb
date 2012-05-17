SUMMARY = "General-purpose fonts released by Google as part of Android"
DESCRIPTION = "\
The Droid typeface family was designed in the fall of 2006 by Ascender's \
Steve Matteson, as a commission from Google to create a set of system fonts \
for its Android platform. The goal was to provide optimal quality and comfort \
on a mobile handset when rendered in application menus, web browsers and for"
SECTION = "x11/fonts"
LICENSE = "ASL 2.0"
LIC_FILES_CHKSUM = "file://NOTICE;md5=9645f39e9db895a4aa6e02cb57294595"
PR = "r0"
RDEPENDS_${PN} = "fontconfig-utils"

inherit allarch

SRC_URI = "http://ftp.de.debian.org/debian/pool/main/f/fonts-droid/fonts-droid_20111207+git.orig.tar.bz2"

S = "${WORKDIR}/fonts-droid-${PV}+git"

do_install () {
        install -d ${D}${prefix}/share/fonts/ttf/
        for i in *.ttf; do
                install -m 644 $i ${D}${prefix}/share/fonts/ttf/${i}
        done

	# fontconfig ships this too.  not sure what to do about it.
        #install -d ${D}${sysconfdir}/fonts
        #install -m 644 local.conf ${D}${sysconfdir}/fonts/local.conf
}

pkg_postinst_${PN} () {
#!/bin/sh
fc-cache
}


FILES_${PN} = "/etc ${datadir}/fonts"

SRC_URI[md5sum] = "dcf306262c3182617e5c5a7ed0a9c43a"
SRC_URI[sha256sum] = "fa1a11b30f524052436b1c2f36d06870841379f4d79408a129426a5ca8c9dad8"

