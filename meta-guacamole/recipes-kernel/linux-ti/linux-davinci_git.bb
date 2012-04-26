require multi-kernel.inc

DESCRIPTION = "Linux kernel for DaVinci processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "hawkboard"

DEFAULT_PREFERENCE_hawkboard = "1"

BRANCH = "master"

SRC_URI_append = " file://defconfig "

S = "${WORKDIR}/git"

# The main PR is now using MACHINE_KERNEL_PR, for davinci see conf/machine/include/davinci.inc

# OMAPL tracking master branch - PSP 3.20.00.12

ARAGO_L1_REV = "2acf935c01b9adb50164d421c40cdc5862b9e143"
ARAGO_L1_BR  = "master"
ARAGO_L1_PV  = "2.6.32+2.6.33-rc4-${PR}+gitr${SRCREV}"
ARAGO_L1_URI = "git://arago-project.org/git/projects/linux-omapl1.git;protocol=git;branch=${BRANCH} "

SRCREV_hawkboard                  = "${ARAGO_L1_REV}"

PV_omapl138             = "${ARAGO_L1_PV}"

BRANCH_omapl138         = "${ARAGO_L1_BR}"

SRC_URI_append_omapl138 = "${ARAGO_L1_URI}"

SRC_URI_append_omapl138 = " file://logo_linux_clut224.ppm \
                                     file://0001-ahci-ti-Fix-currently-harmless-typo-in-SATA-PHY.patch \
                                     file://0002-ahci-ti-Update-SATA-PHY-configuration-RXCDR.patch \
                                     file://0001-board-da850-evm-Disable-NAND-SUBPAGE.patch \
                                     file://0001-uio_pruss1-Core-driver-addition.patch \
                                     file://0002-uio_pruss2-Platform-changes.patch \
                                     file://0003-uio_pruss3-Workarounds-put-into-core-code.patch \
                                     file://0001-cgroupfs-create-sys-fs-cgroup-to-mount-cgroupfs-on.patch \
                                     file://0001-ARM-6329-1-wire-up-sys_accept4-on-ARM.patch \
                                    "

SRC_URI_append_hawkboard          = " \
                                     file://patch-2.6.33rc4-psp-to-hawkboard.patch \
                                     file://0001-board-da850-hawk-Disable-NAND-SUBPAGE.patch \
                                    "

# Perf in 2.6.33rc has broken perl handling, so disable it
do_compile_perf() {
    :
}

do_install_perf() {
    :
}

