require clutter.inc
require clutter-git.inc

DEFAULT_PREFERENCE = "1"

DEPENDS = "${STDDEPENDS} ${STDDEPENDS_GLX}"
RDEPENDS = "${STDRDEPENDS} ${STDRDEPENDS_GLX}"
CONFLICTS += "clutter-1.10-egl"

EXTRA_OECONF = "${BASE_CONF} ${BASE_CONF_GLX}"
