require multi-kernel.inc

DESCRIPTION = "Linux kernel for TI81xx processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "am387x-evm|am389x-evm|c6a814x-evm|c6a816x-evm|dm814x-evm"

# This is on the ti81xx-master branch
SRCREV = "745a06cb913ad7a9ab44855aa36d8c012cd5793b"

# The main PR is now using MACHINE_KERNEL_PR, for omap3 see conf/machine/include/omap3.inc
MACHINE_KERNEL_PR_append = "a+gitr${SRCREV}"

SRC_URI += "git://arago-project.org/git/projects/linux-omap3.git;protocol=git;branch=ti81xx-master \
            file://0001-ti814x-added-code-for-disabling-the-least-significan.patch \
            file://defconfig"

S = "${WORKDIR}/git"

# Perf in 2.6.37-psp doesn't build, so disable it
do_compile_perf() {
    :
}

do_install_perf() {
    :
}

