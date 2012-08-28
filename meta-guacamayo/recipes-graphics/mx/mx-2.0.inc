
# Until release, pull from git
#SRC_URI = "http://source.clutter-project.org/sources/mx/2.0/mx-${PV}.tar.xz"
#SRC_URI[sha256sum] = "20fd8e1463d84c791c20dd1e021bc18041b05ad095a1acbaace7bf0dd43d09f2"

SRCREV = "e4b4310f5237a4c53f6857cf3261637f3100c831"
PV = "2.0.0+git${SRCPV}"

SRC_URI = "git://github.com/clutter-project/mx.git;proto=git \
	   file://mx-combo-box-populate.patch \
	  "
S = "${WORKDIR}/git"


