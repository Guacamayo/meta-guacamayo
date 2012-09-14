
SRCREV = "6de0ef28283623064b95c2f6280ec348456bb6f6"
PV = "1.6.0+git${SRCPV}"
INCPR_append = ".0"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://clutter-gst/clutter-gst.h;beginline=1;endline=24;md5=95baacba194e814c110ea3bdf25ddbf4"


SRC_URI = "git://git.gnome.org/clutter-gst;protocol=git;branch=master\
           file://${COREBASE}/meta/recipes-graphics/clutter/clutter-gst/enable_tests-1.8.patch"

S = "${WORKDIR}/git"

