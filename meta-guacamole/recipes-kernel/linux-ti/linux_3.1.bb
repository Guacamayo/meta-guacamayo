require linux.inc

COMPATIBLE_MACHINE = "beagleboard"

DESCRIPTION = "Linux kernel for TI processors"
KERNEL_IMAGETYPE = "uImage"

DEFAULT_PREFERENCE = "-99"

PV = "3.0+3.1rc"
SRCREV_pn-${PN} = "9e79e3e9dd9672b37ac9412e9a926714306551fe"

# The main PR is now using MACHINE_KERNEL_PR, for omap3 see conf/machine/include/omap3.inc
MACHINE_KERNEL_PR_append = "e"

SRC_URI += "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=git \
            file://beagle/0001-UNFINISHED-OMAP3-beagle-add-support-for-expansionboa.patch \
            file://beagle/0002-HACK-OMAP3-beagle-switch-to-GPTIMER1.patch \
            file://madc/0001-Enabling-Hwmon-driver-for-twl4030-madc.patch \
            file://madc/0002-mfd-twl-core-enable-madc-clock.patch \
            file://sgx/0001-ARM-L2-Add-and-export-outer_clean_all.patch \
            file://fixes/vout.patch \
            file://defconfig"

# Needs refresh:
#            file://beagle/0003-OMAP3-beagle-HACK-add-in-1GHz-OPP.patch \


SRC_URI_append_beagleboard = " file://logo_linux_clut224.ppm \
"

S = "${WORKDIR}/git"

