require clutter-1.12.inc
require clutter-1.12-git.inc

PR = "${INCPR}.1"

SRC_URI += "file://fix-navkeys.patch"

DEPENDS = "${STDDEPENDS} ${STDDEPENDS_GLX}"
RDEPENDS_${PN} = "${STDRDEPENDS} ${STDRDEPENDS_GLX}"

EXTRA_OECONF = "${BASE_CONF} ${BASE_CONF_GLX}"
