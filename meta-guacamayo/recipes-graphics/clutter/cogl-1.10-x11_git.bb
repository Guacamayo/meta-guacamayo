require cogl.inc
require cogl-git.inc

CONFLICTS += "cogl-1.10-egl"

# the 'normal' config: big gl over X11
DEPENDS = "${STDDEPENDS} ${STDDEPENDS_X11} ${STDDEPENDS_GL}"
DEPENDS_beagleboard = "${STDDEPENDS} ${STDDEPENDS_X11} ${STDDEPENDS_EGL}"

RDEPENDS += "${STDRDEPENDS} ${STDRDEPENDS_GL}"
RDEPENDS_beagleboard += "${STDRDEPENDS} ${STDRDEPENDS_GLES2}"

DEFAULT_PREFERENCE = "1"

EXTRA_OECONF             = "${BASE_CONF} ${BASE_CONF_X11_GL}"
EXTRA_OECONF_beagleboard = "${BASE_CONF} ${BASE_CONF_X11_GLES}"

