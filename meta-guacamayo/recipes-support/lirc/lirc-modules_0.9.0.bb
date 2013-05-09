DESCRIPTION = "LIRC is a package that allows you to decode and send infra-red signals of many commonly used remote controls. This package contains the lirc kernel modules."
SECTION = "base"
PRIORITY = "optional"
HOMEPAGE = "http://www.lirc.org"
LICENSE = "GPLv2"
DEPENDS = "virtual/kernel setserial"

PR = "${INCPR}.2"

inherit autotools module

require lirc-config.inc

# Patches needed for 3.8 kernels, at the moment that's just linux-yocto
SRC_URI_append_x86 = " file://fix-for-3.4.patch file://fix-for-3.8.patch"
SRC_URI_append_nuc = " file://fix-for-3.4.patch file://fix-for-3.8.patch"

MAKE_TARGETS = "KERNEL_PATH=${STAGING_KERNEL_DIR} MAKE='make V=1' -C drivers"

fakeroot do_install() {
	oe_runmake -C drivers DESTDIR="${D}" moduledir="/lib/modules/${KERNEL_VERSION}/lirc" install
	rm -rf ${D}/dev
}

# this is for distributions that don't use udev
pkg_postinst_${PN}_append() {
if [ ! -c $D/dev/lirc -a ! -f /sbin/udevd ]; then mknod $D/dev/lirc c 61 0; fi
}

FILES_${PN} = "/lib/modules"
