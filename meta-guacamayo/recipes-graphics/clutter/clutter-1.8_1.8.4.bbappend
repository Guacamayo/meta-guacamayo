
PRINC = "1"

DEPENDS_beagleboard = "${STDDEPENDS} virtual/egl"

EXTRA_OECONF_beagleboard = "${BASE_CONF}  --with-flavour=eglx"
