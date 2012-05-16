DESCRIPTION = "For us to know and for you to wonder ..."
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r0"

# Extra Image features
GUACAMAYO_FEATURES =+ "package-management   \
		       guacamayo-mex-x11    \
		       guacamayo-restricted \
		      "

inherit guacamayo-image
