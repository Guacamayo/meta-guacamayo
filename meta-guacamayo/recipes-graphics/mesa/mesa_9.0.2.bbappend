PACKAGE_ARCH = "${MACHINE_ARCH}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-8.0:"

DEFAULT_PREFERENCE_atom-pc = "10"

EXTRA_OECONF += "--enable-shared-glapi --enable-gbm --enable-egl"
