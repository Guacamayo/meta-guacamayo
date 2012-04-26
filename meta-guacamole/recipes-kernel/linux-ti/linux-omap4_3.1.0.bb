COMPATIBLE_MACHINE = "pandaboard"

require linux.inc

# The main PR is now using MACHINE_KERNEL_PR, for omap4 see conf/machine/include/omap4.inc
MACHINE_KERNEL_PR_append = "a"

CORTEXA8FIXUP = "no"

# ti-ubuntu-3.1.0-1282.11
SRCREV = "a5c60c099296fcfc0c8fa8085c40883971486512"

SRC_URI = "git://dev.omapzoom.org/pub/scm/integration/kernel-ubuntu.git;protocol=git;branch=ti-ubuntu-3.1-1282 \
           file://0001-Makefile.fwinst-fix-install-breakage-for-FW-images-r.patch \
           file://defconfig \
           "

S = "${WORKDIR}/git"
