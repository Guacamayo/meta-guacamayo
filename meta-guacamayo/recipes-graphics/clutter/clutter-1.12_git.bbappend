# Guacamayo-specific clutter config

# machine-specific package, because we choose based on machine features
PACKAGE_ARCH = "${MACHINE_ARCH}"

PRINC = "1"

X11_DEPENDS      = "${STDDEPENDS} ${STDDEPENDS_GLX}"
X11_RDEPENDS     = "${STDRDEPENDS} ${STDRDEPENDS_GLX}"
X11_EXTRA_OECONF = "${BASE_CONF} ${BASE_CONF_GLX}"

EGL_DEPENDS      = "${STDDEPENDS} ${STDDEPENDS_EGL}"
EGL_RDEPENDS     = "${STDRDEPENDS} ${STDRDEPENDS_EGL}"
EGL_EXTRA_OECONF = "${BASE_CONF} ${BASE_CONF_EGL}"

DEPENDS = "${@base_contains("MACHINE_FEATURES", "guacamayo-x11", "${X11_DEPENDS}", "${EGL_DEPENDS}", d)}"
RDEPENDS = "${@base_contains("MACHINE_FEATURES", "guacamayo-x11", "${X11_RDEPENDS}", "${EGL_RDEPENDS}", d)}"
EXTRA_OECONF = "${@base_contains("MACHINE_FEATURES", "guacamayo-x11", "${X11_EXTRA_OECONF}", "${EGL_EXTRA_OECONF}", d)}"
