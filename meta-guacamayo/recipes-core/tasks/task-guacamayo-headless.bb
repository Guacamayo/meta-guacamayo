DESCRIPTION="Guacamayo task for headless setups"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r1"

DEPENDS += "alsa-plugins"

PACKAGES="task-guacamayo-headless"

ALLOW_EMPTY = "1"

RDEPENDS_task-guacamayo-headless = "guacamayo-session-headless"

