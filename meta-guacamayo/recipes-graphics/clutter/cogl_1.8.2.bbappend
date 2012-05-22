
PRINC = "1"

DEPENDS_beagleboard = "${STDDEPENDS} virtual/egl libxcomposite"

EXTRA_OECONF_beagleboard = "${BASE_CONF} --enable-gles2 --disable-gl --disable-glx"
