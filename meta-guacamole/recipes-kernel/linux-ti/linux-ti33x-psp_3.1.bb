SECTION = "kernel"
DESCRIPTION = "Linux kernel for TI33x EVM from PSP, based on am335x-kernel"
LICENSE = "GPLv2"
KERNEL_IMAGETYPE = "uImage"

require multi-kernel.inc
require tipspkernel.inc

S = "${WORKDIR}/git"

MULTI_CONFIG_BASE_SUFFIX = ""

BRANCH = "v3.1-meta-ti-r1r+gitr1d84d8853fa30cf3db2571a5aec572accca4e29d"
SRCREV = "1d84d8853fa30cf3db2571a5aec572accca4e29d"
MACHINE_KERNEL_PR_append = "l+gitr${SRCREV}"

COMPATIBLE_MACHINE = "(ti33x)"

SRC_URI += "git://github.com/beagleboard/linux.git;branch=${BRANCH} \
	file://defconfig"

PATCHES_OVER_PSP = " \
	file://0001-f_rndis-HACK-around-undefined-variables.patch \
	file://0001-ARM-omap-am335x-BeagleBone-version-detection-and-sup.patch \
	file://0002-ARM-OMAP2-beaglebone-add-LED-support.patch \
	file://0003-ARM-OMAP2-beaglebone-add-DVI-support-needs-cleanup.patch \
	file://0004-da8xx-fb-add-DVI-support-for-beaglebone.patch \
	file://0001-usb-musb_core-kill-all-global-and-static-variables.patch \
	file://0002-arm-omap-am335x-correct-32KHz-clk-rate.patch \
	file://0003-arm-omap-mcspi-correct-memory-range-when-requesting-.patch \
	file://0004-arm-omap-mcspi-follow-proper-pm_runtime-enable-disab.patch \
	file://0005-arm-omap-mcspi-follow-proper-probe-remove-steps.patch \
	file://0006-usb-musb-cppi41_dma-Check-if-scheduling-is-required-.patch \
	file://can/0001-can-d_can-Added-support-for-Bosch-D_CAN-controller.patch \
	file://can/0002-can-d_can-Added-platform-data-for-am33xx-device.patch \
	file://can/0003-can-d_can-DCAN-config-added-to-am335x_evm_defconfig.patch \
	file://can/0004-can-d_can-fix-for-cansend-loop-issue.patch \
	file://can/0005-can-d_can-fixes-the-rmmod-crash.patch \
	file://i2c/0001-arm-omap-mux33xx-Add-i2c2-pin-muix.patch \
	file://i2c/0002-omap-hwmod-33xx-Add-support-for-third-i2c-bus.patch \
	file://i2c/0003-arm-omap-board-Add-quick-hack-to-get-i2c2-bus-suppor.patch	\
	file://adc/0001-AM335x-Add-support-for-TSC-on-Beta-GP-EVM.patch \
	file://adc/0002-ARM-OMAP-AM335x-Add-support-for-Beta-GP-EVM.patch \
	file://adc/0003-AM335x-Add-support-for-pressure-measurement-on-TSC.patch \
	file://adc/0004-tscadc-Add-general-purpose-mode-untested-with-touchs.patch \
	file://adc/0005-tscadc-Add-board-file-mfd-support-fix-warning.patch \
	file://adc/0006-AM335X-init-tsc-bone-style-for-new-boards.patch \
	file://adc/0007-tscadc-make-stepconfig-channel-configurable.patch \
	file://adc/0008-tscadc-Trigger-through-sysfs.patch \
	file://adc/0009-meta-ti-Remove-debug-messages-for-meta-ti.patch \
	file://adc/0010-tscadc-switch-to-polling-instead-of-interrupts.patch \
	file://st7735fb/0001-st7735fb-WIP-framebuffer-driver-supporting-Adafruit-.patch \
	file://0031-am335x-evm-add-pdata-for-all-cape-EEPROM-permutation.patch \
	file://0032-am335x-add-support-for-7-LCD-cape-fix-DVI-entries.patch \
	file://0033-beaglebone-update-DVI-cape-partnumber.patch \
	file://0034-beaglebone-really-enable-i2c2-pullups-fixes-timeouts.patch \
	file://0035-beaglebone-add-structs-for-DVI-cape-LEDs.patch\
	file://0036-beaglebone-update-LCD-cape-partnumber.patch \
	file://0037-beaglebone-compare-complete-partnumber-not-the-first.patch \
	file://0038-omap_hsmmc-Set-dto-to-max-value-of-14-to-avoid-SD-Ca.patch \
	"

SRC_URI_append_beaglebone = " file://logo_linux_clut224.ppm"
