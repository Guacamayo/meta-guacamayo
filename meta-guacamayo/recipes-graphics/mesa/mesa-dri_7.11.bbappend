FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-7.11:"

DEFAULT_PREFERENCE_atom-pc = "10"

EXTRA_OECONF += "--enable-shared-glapi --enable-gbm --enable-egl"

PROVIDES += "virtual/egl"

PACKAGES =+ "libgbm libgbm-dev libgbm-dbg"

FILES_libgbm = "${libdir}/libgbm.so.*"
FILES_libgbm-dev = "${libdir}/libgbm* ${libdir}/pkgconfig/gbm.pc ${includedir}/gbm.h"
FILES_libgbm-dbg += "${libdir}/gbm/.debug/libgbm*"
