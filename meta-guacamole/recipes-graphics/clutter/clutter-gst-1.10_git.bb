require ${GUACABASE}/meta/recipes-graphics/clutter/clutter-gst.inc

PR = "r0"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://clutter-gst/clutter-gst.h;beginline=1;endline=24;md5=95baacba194e814c110ea3bdf25ddbf4"

DEPENDS += "clutter-1.10 gstreamer"
RDEPENDS_${PN} += "gst-meta-base"

SRCREV = "b96101a3ec2641d7f014934006e04d5aa36eea31"

SRC_URI = "git://git.gnome.org/clutter-gst;protocol=git;branch=master\
           file://${GUACABASE}/meta/recipes-graphics/clutter/clutter-gst/enable_tests-1.8.patch"

S = "${WORKDIR}/git"

SRC_URI[md5sum] = "98aeed6baa7b3a80f10e7733080eb088"
SRC_URI[sha256sum] = "54e2966e507563bd3f123a4e25780c2487f854c6cc3df3bedc8c6b5eb45e9769"

do_configure_prepend () {
       # Disable DOLT
       sed -i -e 's/^DOLT//' ${S}/configure.ac
}
