DESCRIPTION = "For us to know and for you to wonder ..."
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${THISDIR}/../../COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r1"

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

inherit guacamayo-image

GUACA_DEMOS_FEATURE = "${@base_contains("IMAGE_FEATURES", "guacamayo-demos", "task-guacamayo-demos-audio task-guacamayo-demos-video task-guacamayo-demos-pictures", "", d)}"

IMAGE_INSTALL += "${GUACA_DEMOS_FEATURE}"

export RPI_GPU_FIRMWARE="arm128"
