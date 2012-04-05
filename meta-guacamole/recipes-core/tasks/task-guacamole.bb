DESCRIPTION="Guacamole tasks"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamole/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r0"

PACKAGES="task-guacamole-core"

ALLOW_EMPTY = "1"

RDEPENDS_task-guacamole-core = "\
			     rygel \
                               "