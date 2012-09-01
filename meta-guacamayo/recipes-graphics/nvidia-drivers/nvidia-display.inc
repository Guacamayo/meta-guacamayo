# nvidia-display .bb build file
# Copyright (C) 2005-2006, Advanced Micro Devices, Inc.  All Rights Reserved
# Copyright (C) 2012, sleep(5) ltd
# Released under the MIT license (see /COPYING)

NVDISPLAYPR = "0"

DEFAULT_PREFERENCE = "-1"
DEFAULT_PREFERENCE_atom-nvidia = "1"

LIC_FILES_CHKSUM = "file://LICENSE;md5=d88b0db2ba7f6dfe70852c64b2fce6ce"

SRC_URI="http://download.nvidia.com/XFree86/Linux-${NVIDIA_ARCH}/${PV}/${PKG_BASENAME}.run \
	file://nvidia-oe-conftest.patch"

S="${WORKDIR}/${PKG_BASENAME}/kernel"

PROVIDES = "virtual/libgl"

PACKAGES += "libgl-nvidia libgl-nvidia-dev xf86-video-nvidia xf86-video-nvidia-dev"

# Main package: kernel module and utilities
FILES_${PN} += "${bindir}"

# libgl-nvidia contains the libgl bits
FILES_libgl-nvidia = "${libdir}/*.so.* ${libdir}/tls"
FILES_libgl-nvidia-dev = "${libdir}/*.so ${includedir}"
RPROVIDES_libgl-nvidia = "libgl"
RDEPENDS_libgl-nvidia += "${PN}"

# the xserver driver and glx extension
FILES_xf86-video-nvidia = "${libdir}/xorg/modules/drivers/*_drv.so \
			   ${libdir}/xorg/modules/extensions/*.so.* \
			  "
RDEPENDS_xf86-video-nvidia += "${PN}"

FILES_xf86-video-nvidia-dev = " \
         ${libdir}/*.so ${libdir}/xorg/modules/extensions/libglx.so"

FILES_${PN}-doc = "${datadir}/doc/nvidia"

INSANE_SKIP_${PN} = "1"

do_configure() {
	rm -f ${S}/makefile
	if [ "${KERNEL_PATCHLEVEL}" != "4" ] ; then
		ln -sf Makefile.kbuild ${S}/Makefile
	else
		ln -sf Makefile.nvidia ${S}/Makefile
	fi
}

do_install() {
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake DEPMOD=echo INSTALL_MOD_PATH="${D}" CC="${KERNEL_CC}" LD="${KERNEL_LD}" install

	install -d ${D}/usr/bin
	install -d ${D}/usr/lib/xorg/modules/drivers/
	install -d ${D}/usr/lib/xorg/modules/extensions/
	install -d ${D}/usr/include/GL
	install -d ${D}/usr/share/doc/nvidia

	cp -pPR ${WORKDIR}/${PKG_BASENAME}/html ${D}/usr/share/doc/nvidia/

        # this is the driver package -- move these so they don't end up in the
        # libgl package as well.
	mv ${WORKDIR}/${PKG_BASENAME}/*_drv.so \
					${D}/usr/lib/xorg/modules/drivers
	mv ${WORKDIR}/${PKG_BASENAME}/*glx.so.* \
					${D}/usr/lib/xorg/modules/extensions

	cp -pPR ${WORKDIR}/${PKG_BASENAME}/*.so.* ${D}/usr/lib
	cp -pPR ${WORKDIR}/${PKG_BASENAME}/tls ${D}/usr/lib

	cp -pPR ${WORKDIR}/${PKG_BASENAME}/*.h ${D}/usr/include/GL

	cp -p ${WORKDIR}/${PKG_BASENAME}/nvidia-settings ${D}/usr/bin

	ln -s libglx.so.${PV} ${D}/usr/lib/xorg/modules/extensions/libglx.so

	ln -s libGL.so.${PV} ${D}/usr/lib/libGL.so
	ln -s libGL.so.${PV} ${D}/usr/lib/libGL.so.1

	ln -s libOpenCL.so.${PV} ${D}/usr/lib/libOpenCL.so
	ln -s libOpenCL.so.${PV} ${D}/usr/lib/libOpenCL.so.1
}
