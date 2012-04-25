DESCRIPTION = "Kernel drivers for the PowerVR SGX chipset found in the omap3 SoCs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=21228a42e27d1d104b31a83f7c9da935"

TI_BIN_UNPK_CMDS="Y: qY:workdir:Y"
require ${GUACABASE}/meta-ti/recipes-ti/includes/ti-eula-unpack.inc

SGXPV = "4_05_00_03"
BINFILE := "Graphics_SDK_setuplinux_${SGXPV}.bin"

inherit module

MACHINE_KERNEL_PR_append = "b"
PR = "${MACHINE_KERNEL_PR}"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/${SGXPV}/exports/Graphics_SDK_setuplinux_${SGXPV}.bin \
          "
SRC_URI[md5sum] = "0e651eaa92bb91760f0b40a17697a7dc"
SRC_URI[sha256sum] = "bfe764a8959556195545d6fff76f63a489642f345c105bbbc309a3f243c2dd0e"
TI_BIN_UNPK_WDEXT="/Graphics_SDK_${SGXPV}"
S = "${WORKDIR}${TI_BIN_UNPK_WDEXT}/GFX_Linux_KM"

PVRBUILD = "release"
export KERNELDIR = "${STAGING_KERNEL_DIR}"

PACKAGE_STRIP = "no"

TI_PLATFORM_omap3 = "omap3630"
TI_PLATFORM_ti814x = "ti81xx"
TI_PLATFORM_ti816x = "ti81xx"
TI_PLATFORM_ti33x = "ti335x"

MODULESLOCATION_omap3 = "dc_omapfb3_linux"
MODULESLOCATION_ti814x = "dc_ti81xx_linux"
MODULESLOCATION_ti816x = "dc_ti81xx_linux"
MODULESLOCATION_ti33x = "dc_ti335x_linux"

MAKE_TARGETS = " BUILD=${PVRBUILD} TI_PLATFORM=${TI_PLATFORM}"

do_install() {
	mkdir -p ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/gpu/pvr
	cp ${S}/pvrsrvkm.ko \
	   ${S}/services4/3rdparty/${MODULESLOCATION}/omaplfb.ko  \
	   ${S}/services4/3rdparty/bufferclass_ti/bufferclass_ti.ko \
	   ${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers/gpu/pvr
}
