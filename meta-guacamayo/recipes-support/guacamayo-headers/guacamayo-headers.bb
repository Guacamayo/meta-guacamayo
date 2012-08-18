DESCRIPTION = "C Headers for Guacamayo"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://guacamayo-version.h;endline=21;md5=3a7f40462d61627be4d3f2e90025e76c"
HOMEPAGE = "http://guacamayo-project.org"

inherit allarch

PR = "r0"

SRC_URI = "file://guacamayo-version.h"

S = "${WORKDIR}"

do_install (){
    # generate guacamayo-version.h from what is defined in guacamayo.conf
    DATENOW=`date -u`
    sed -i -e "s/@GUACAMAYO_VERSION_MAJOR@/${GUACA_VERSION_MAJOR}/" \
           -e "s/@GUACAMAYO_VERSION_MINOR@/${GUACA_VERSION_MINOR}/" \
           -e "s/@GUACAMAYO_VERSION_MICRO@/${GUACA_VERSION_MICRO}/" \
           -e "s/@GUACAMAYO_VERSION_STRING@/${GUACA_VERSION_MAJOR}.${GUACA_VERSION_MINOR}.${GUACA_VERSION_MICRO}/" \
           -e "s/@GUACAMAYO_DISTRO_STRING@/${GUACA_DISTRO_NAME} ${GUACA_VERSION_MAJOR}.${GUACA_VERSION_MINOR}.${GUACA_VERSION_MICRO}${GUACA_VERSION_NOTE}/" \
           -e "s/@GUACAMAYO_BUILD_DATE_STRING@/${DATENOW}/" \
           ${S}/guacamayo-version.h

    install -d ${D}${includedir}
    install ${S}/*.h ${D}${includedir}
}

FILES_${PN}-dev = "${includedir}/*"
