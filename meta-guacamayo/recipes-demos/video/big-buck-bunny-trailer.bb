
SUMMARY = "Big Buck Bunny Trailer"
DESCRIPTION = "Big Buck Bunny Trailer"
HOMEPAGE = "http://www.bigbuckbunny.org"
LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=927e60078c187c751e401061d15d9bc8"

PR = "r1"

FILESEXTRAPATHS_prepend := "${THISDIR}/big-buck-bunny:"

SRC_URI[md5sum] = "d4ec20a0c5eb7be376176804d1fd528a"
SRC_URI[sha256sum] = "cb7be60feb4fd4ff5b006e35e824201a48361b7ebc2f2bbcaf3351912035561a"

SRC_URI = "http://mirror.cs.umn.edu/blender.org/peach/trailer/trailer_720p.mov \
	   file://LICENSE"

inherit allarch

do_configure_prepend () {
    cp ${WORKDIR}/LICENSE ${S}
}

do_install() {
    install -d ${D}${datadir}/demos/video/
    install -m 0644 ${WORKDIR}/trailer_720p.mov ${D}${datadir}/demos/video/big_buck_bunny_trailer.mov
    install -m 0644 ${WORKDIR}/LICENSE ${D}${datadir}/demos/video/LICENSE.big_buck_bunny_trailer
}

FILES_${PN} += "${datadir}"
