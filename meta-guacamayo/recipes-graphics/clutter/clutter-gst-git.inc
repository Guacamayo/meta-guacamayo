
SRCREV = "1df53639f9cb36e127963d0a2518d753ac7c1868"
PV = "1.5.6+git${SRCPV}"
PR = "${INCPR}.0"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://clutter-gst/clutter-gst.h;beginline=1;endline=24;md5=95baacba194e814c110ea3bdf25ddbf4"


SRC_URI = "git://git.gnome.org/clutter-gst;protocol=git;branch=master\
           file://${GUACABASE}/meta/recipes-graphics/clutter/clutter-gst/enable_tests-1.8.patch"

S = "${WORKDIR}/git"

