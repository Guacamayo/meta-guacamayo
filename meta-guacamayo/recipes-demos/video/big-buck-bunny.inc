
SUMMARY = "Big Buck Bunny"
DESCRIPTION = "Big Buck Bunny"
HOMEPAGE = "http://www.bigbuckbunny.org"
LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=927e60078c187c751e401061d15d9bc8"

FILESEXTRAPATHS_prepend := "${THISDIR}/big-buck-bunny:"

SRC_URI = "http://blender-mirror.kino3d.org/peach/bigbuckbunny_movies/big_buck_bunny_${BUNNY_FLAVOUR} \
	   file://LICENSE"

inherit allarch

do_configure_prepend () {
    cp ${WORKDIR}/LICENSE ${S}
}

do_install() {
    install -d ${D}${datadir}/demos/video/
    install -m 0644 ${WORKDIR}/big_buck_bunny_${BUNNY_FLAVOUR} ${D}${datadir}/demos/video/
}

FILES_${PN} += "${datadir}"
