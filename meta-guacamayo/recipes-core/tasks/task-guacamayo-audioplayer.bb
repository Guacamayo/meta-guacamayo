DESCRIPTION="Guacamayo task for audioplayers"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${THISDIR}/../../COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r0"

DEPENDS += "alsa-plugins"

PACKAGES="task-guacamayo-audioplayer"

ALLOW_EMPTY = "1"

RDEPENDS_task-guacamayo-audioplayer = "task-guacamayo-renderer guacamayo-session-audioplayer"

