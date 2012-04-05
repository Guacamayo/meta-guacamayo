DESCRIPTION = "For us to know and for you to wonder ..."
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamole/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r1"

# for now use SATO features.
IMAGE_FEATURES += "apps-console-core ${SATO_IMAGE_FEATURES}"

inherit core-image

GUACAMOLE_TASKS = " \
		   task-guacamole-core \
		   task-guacamole-restricted \
		  "

IMAGE_INSTALL += "${GUACAMOLE_TASKS}"
