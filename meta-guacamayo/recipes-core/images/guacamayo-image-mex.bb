DESCRIPTION = "For us to know and for you to wonder ..."
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${THISDIR}/../../COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r2"

# Select appropriate MEX flavour based on machine features
GUACA_MEX_X11 = "guacamayo-mex-x11		\
	         guacamayo-mex-x11-tests	\
		"
GUACA_MEX_EGL = "guacamayo-mex-egl		\
	         guacamayo-mex-egl-tests	\
		"

GUACA_MEX = "${@base_contains("MACHINE_FEATURES", "guacamayo-x11", "${GUACA_MEX_X11}", "${GUACA_MEX_EGL}", d)}"

# Extra Image features
GUACAMAYO_FEATURES =+ "package-management		\
		       guacamayo-restricted-core	\
		       guacamayo-restricted-audio	\
		       guacamayo-restricted-video	\
		       ${GUACA_MEX}			\
		      "

# Config for RaspberryPi, guacamayo-image class makes use of these
# lage-ish GPU memory
GPU_MEM_256 ?= "192"
GPU_MEM_512 ?= "256"

# and overclock
ARM_FREQ ?= "1000"
CORE_FREQ ?= "500"
SDRAM_FREQ ?= "500"
OVER_VOLTAGE ?= "6"

inherit guacamayo-image

GUACA_DEMOS_FEATURE = "${@base_contains("IMAGE_FEATURES", "guacamayo-demos", "task-guacamayo-demos-audio task-guacamayo-demos-video task-guacamayo-demos-pictures", "", d)}"

IMAGE_INSTALL += "${GUACA_DEMOS_FEATURE}"

