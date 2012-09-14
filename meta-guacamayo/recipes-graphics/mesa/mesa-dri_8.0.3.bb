
include ${COREBASE}/meta/recipes-graphics/mesa/mesa-common.inc
include mesa-8.0.inc
include ${COREBASE}/meta/recipes-graphics/mesa/mesa-dri.inc

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI[md5sum] = "cc5ee15e306b8c15da6a478923797171"
SRC_URI[sha256sum] = "96812c59b0923314914185a2b064eb5245f1228a319c4db94835ce1c84da407f"

LIC_FILES_CHKSUM = "file://docs/license.html;md5=03ccdc4c379c4289aecfb8892c546f67"

PR = "${INC_PR}.1"
