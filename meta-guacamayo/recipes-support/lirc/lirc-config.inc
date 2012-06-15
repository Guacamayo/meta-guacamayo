INCPR = "r0"

SRC_URI = "http://prdownloads.sourceforge.net/lirc/lirc-${PV}.tar.bz2"
SRC_URI[md5sum] = "b232aef26f23fe33ea8305d276637086"
SRC_URI[sha256sum] = "6323afae6ad498d4369675f77ec3dbb680fe661bea586aa296e67f2e2daba4ff"

LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3"

S = "${WORKDIR}/lirc-${PV}"

EXTRA_OECONF = "--with-kerneldir=${STAGING_KERNEL_DIR} ${DRIVER}"

PARALLEL_MAKE = ""

DRIVER ?= "--with-driver=serial"
# fails to build at the moment, re-enable later
#DRIVER_collie = "--with-driver=sa1100"
DRIVER_h3600 = "--with-driver=sa1100"
DRIVER_simpad = "--with-driver=sa1100"
DRIVER_nslu2 = "--with-driver=nslu2"
