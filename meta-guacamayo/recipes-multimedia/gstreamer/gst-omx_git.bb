DEPENDS = "gstreamer"
LICENSE = "LGPLv2.1"
LICENSE_FLAGS = "commercial"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

RDEPENDS_raspberrypi = "libopenmaxil"

PR = "r2"

BRANCH = "0.10"
BRANCH_raspberrypi = "raspberry"

SRCREV="27a711debd8a2832b4b58db778289ec35400014f"
SRCREV_raspberrypi="d2f031808b4e1d51546245ee93cd5524d1ce2f63"

PV = "0.10.0.1+git${SRCPV}"

SRC_URI = "git://anongit.freedesktop.org/gstreamer/gst-omx;branch=${BRANCH};protocol=git\
	file://gstomx.conf"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF += "--disable-valgrind"
acpaths = "-I ${S}/common/m4 -I ${S}/m4"


do_configure_prepend() {
    if test ! -f ${S}common/gst-autogen.sh;
    then
	echo "+ Setting up common submodule"
  	git submodule init
    fi
    git submodule update
}

do_install_append() {
    install -d ${D}${sysconfdir}/xdg
    install -m 0544 ${WORKDIR}/gstomx.conf ${D}${sysconfdir}/xdg
}

FILES_${PN} += "${libdir}/gstreamer-0.10/*.so ${sysconfdir}"
FILES_${PN}-dbg += "${libdir}/gstreamer-0.10/.debug"
FILES_${PN}-dev += "${libdir}/gstreamer-0.10/*.la"
FILES_${PN}-staticdev += "${libdir}/gstreamer-0.10/*.a"

