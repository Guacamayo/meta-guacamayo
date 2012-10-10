
# Until release, pull from git
#SRC_URI = "http://source.clutter-project.org/sources/mx/2.0/mx-${PV}.tar.xz"
#SRC_URI[sha256sum] = "20fd8e1463d84c791c20dd1e021bc18041b05ad095a1acbaace7bf0dd43d09f2"

SRCREV = "5ade5c10966b91fda76402f74385c5336218cc13"
PV = "2.0.0+git${SRCPV}"

SRC_URI = "git://github.com/clutter-project/mx.git;protocol=git \
	   file://mx-combo-box-populate.patch \
	  "
S = "${WORKDIR}/git"


