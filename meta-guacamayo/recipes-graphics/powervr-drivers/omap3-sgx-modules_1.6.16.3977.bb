DESCRIPTION = "Kernel drivers for the PowerVR SGX chipset found in the omap3 SoCs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=21228a42e27d1d104b31a83f7c9da935"

TI_BIN_UNPK_CMDS="Y: qY:workdir:Y"
require ${GUACABASE}/meta-ti/recipes-ti/includes/ti-eula-unpack.inc

SGXPV = "4_03_00_02"
IMGPV = "1.6.16.3977"
BINFILE := "Graphics_SDK_setuplinux_${SGXPV}.bin"

inherit module

MACHINE_KERNEL_PR_append = "a"
PR = "${MACHINE_KERNEL_PR}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/${SGXPV}/exports/Graphics_SDK_setuplinux_${SGXPV}.bin \
           file://Compile-fixes-for-38-kernel.patch \
           file://kernel-30.patch \
          "
SRC_URI[md5sum] = "ff8c1f2b8e4cb42f4ced6a613b081ada"
SRC_URI[sha256sum] = "cdb0bd3964e107733d632aa8224e0537b05c1ffac34befc036423458c8d75255"

S = "${WORKDIR}/Graphics_SDK_${SGXPV}/GFX_Linux_KM"

PVRBUILD = "release"
export KERNELDIR = "${STAGING_KERNEL_DIR}"

PACKAGE_STRIP = "no"

TI_PLATFORM_omap3 = "omap3630"
TI_PLATFORM_ti816x = "ti81xx"

MODULESLOCATION_omap3 = "dc_omap3430_linux"
MODULESLOCATION_ti816x = "dc_ti81xx_linux"

MAKE_TARGETS = " BUILD=${PVRBUILD} TI_PLATFORM=${TI_PLATFORM}"

do_install() {
	mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/gpu/pvr
	cp ${S}/pvrsrvkm.ko \
	   ${S}/services4/3rdparty/${MODULESLOCATION}/omaplfb.ko  \
	   ${S}/services4/3rdparty/bufferclass_ti/bufferclass_ti.ko \
	   ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/gpu/pvr
}
