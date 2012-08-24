require cogl.inc
require cogl-git.inc

CONFLICTS += "cogl-1.10-x11"

# the 'normal' config: egl over big gl
DEPENDS             = "${STDDEPENDS} ${STDDEPENDS_EGL} ${STDDEPENDS_GL}"
DEPENDS_beagleboard = "${STDDEPENDS} ${STDDEPENDS_EGL} ${STDDEPENDS_GLES2}"
DEPENDS_raspberrypi = "${STDDEPENDS} ${STDDEPENDS_EGL} ${STDDEPENDS_GLES2}"

RDEPENDS             += "${STDRDEPENDS} ${STDRDEPENDS_GL}"
RDEPENDS_beagleboard += "${STDRDEPENDS} ${STDRDEPENDS_GLES2}"
RDEPENDS_raspberrypi += "${STDRDEPENDS} ${STDRDEPENDS_GLES2}"

DEFAULT_PREFERENCE = "1"

EXTRA_OECONF             = "${BASE_CONF} ${BASE_CONF_EGL_GL}"
EXTRA_OECONF_beagleboard = "${BASE_CONF} ${BASE_CONF_EGL_GLES}"
EXTRA_OECONF_raspberrypi = "${BASE_CONF} ${BASE_CONF_EGL_GLES}"


