SECTION = "kernel"
DESCRIPTION = "Linux kernel for DaVinci EVM from PSP, based on linux-davinci kernel"
LICENSE = "GPLv2"
KERNEL_IMAGETYPE = "uImage"

require multi-kernel.inc
require tipspkernel.inc

S = "${WORKDIR}/git"

MULTI_CONFIG_BASE_SUFFIX = ""

BRANCH = "03.21.00.03"
SRCREV = "v2.6.37_DAVINCIPSP_03.21.00.04"

COMPATIBLE_MACHINE = "(omapl138)"

SRC_URI += "git://arago-project.org/git/projects/linux-davinci.git;protocol=git;branch=${BRANCH} \
	file://defconfig"

PATCHES_OVER_PSP = " \
	file://0001-TI-WL12xx-MMC-patches-03.21.00.04.patch \
	file://0002-da850-Set-maximum-OPP-frequency-to-456MHz.patch \
	file://0003-AM18xx-WL1271-Enable-BT.patch \
	file://0004-PSP03.21.00.04.sdk-activate-wireless-extensions.patch \
	file://0005-Davinci-da850-Add-Mistral-WL12XX-config-support-to.patch \
	file://0006-Add-wlan-and-BT-config-switches.patch \
	file://uio_pruss.patch \
	"
