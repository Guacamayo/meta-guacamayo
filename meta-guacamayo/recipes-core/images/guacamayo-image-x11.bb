DESCRIPTION = "For us to know and for you to wonder ..."
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r2"

# Extra Image features
GUACAMAYO_FEATURES =+ "package-management   \
		       guacamayo-mex-x11    \
		       guacamayo-restricted \
		       guacamayo-mex-x11-tests \
		      "

inherit guacamayo-image

GUACA_DEMOS_FEATURE = "${@base_contains("IMAGE_FEATURES", "guacamayo-demos", "task-guacamayo-demos-audio task-guacamayo-demos-video task-guacamayo-demos-pictures", "", d)}"

PACKAGE_INSTALL += "${GUACA_DEMOS_FEATURE}"
