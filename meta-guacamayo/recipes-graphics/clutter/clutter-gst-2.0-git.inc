
SRCREV = "c090c303751b74d6614f82183cfa9406e4e2c9a7"
PV = "1.9.90+git${SRCPV}"
INCPR_append = ".0"

LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://clutter-gst/clutter-gst.h;beginline=1;endline=24;md5=95baacba194e814c110ea3bdf25ddbf4"


SRC_URI = "git://git.gnome.org/clutter-gst;protocol=git;branch=master\
           file://${GUACABASE}/meta/recipes-graphics/clutter/clutter-gst/enable_tests-1.8.patch"

S = "${WORKDIR}/git"

