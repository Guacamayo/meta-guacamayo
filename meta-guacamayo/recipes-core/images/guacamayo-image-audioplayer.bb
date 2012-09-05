DESCRIPTION = "Image for UPnP Audioplayer"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r0"

GUACAMAYO_FEATURES =+ "package-management"

GUACAMAYO_FEATURES =+ "					\
		      package-management		\
		      guacamayo-audioplayer		\
		      guacamayo-restricted-core		\
		      guacamayo-restricted-audio	\
		      "

inherit guacamayo-image

GUACA_DEMOS_FEATURE = "${@base_contains("IMAGE_FEATURES", "guacamayo-demos", "task-guacamayo-demos-audio", "", d)}"

PACKAGE_INSTALL += "${GUACA_DEMOS_FEATURE}"