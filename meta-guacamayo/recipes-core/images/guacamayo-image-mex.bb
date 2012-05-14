DESCRIPTION = "For us to know and for you to wonder ..."
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamole/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r0"

GUACAMOLE_FEATURES =+ "package-management"

# Extra Image features
GUACAMOLE_FEATURES =+ "\
		      guacamole-restricted \
		      guacamole-mex \
		      guacamole-gles-tests \
		      "

inherit guacamole-image
