DESCRIPTION = "For us to know and for you to wonder ..."
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamole/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r2"

# For now add X/Sato, though eventually we want to run X-less
GUACAMOLE_FEATURES =+ "x11-base apps-x11-core package-management x11-sato"

# Extra Image features
GUACAMOLE_FEATURES =+ "guacamole-restricted guacamole-mex"

inherit guacamole-image
