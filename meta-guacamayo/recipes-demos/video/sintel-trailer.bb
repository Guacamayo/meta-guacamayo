
SUMMARY = "Sintel Trailer"
DESCRIPTION = "Sintel Trailer"
HOMEPAGE = "http://www.sintel.org"
LICENSE = "CC-BY-3.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d2bde1c1714b10284f00c567f8cb864b"

PR = "r0"

FILESEXTRAPATHS_prepend := "${THISDIR}/sintel:"

SRC_URI[md5sum] = "f62221dc4447d60073335a68cf4ac69f"
SRC_URI[sha256sum] = "cb0fe73fc0a7d543459996c0cdab730997e6eac1013d3ede18796f777cb7f273"

SRC_URI = "http://ftp.nluug.nl/ftp/graphics/blender/apricot/trailer/sintel_trailer-720p.mp4 \
	   file://LICENSE"

inherit allarch

do_configure_prepend () {
    cp ${WORKDIR}/LICENSE ${S}
}

do_install() {
    install -d ${D}${datadir}/demos/video/
    install -m 0644 ${WORKDIR}/sintel_trailer-720p.mp4 ${D}${datadir}/demos/video/
    install -m 0644 ${WORKDIR}/LICENSE ${D}${datadir}/demos/video/LICENSE.sintel
}

FILES_${PN} += "${datadir}"
