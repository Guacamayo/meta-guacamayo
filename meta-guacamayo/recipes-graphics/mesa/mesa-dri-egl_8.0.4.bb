
# This recipe attempts to build mesa as a pure EGL/GLES2 platform, but
# MESA screws us left, right, front, and, for good measure, back too
#
# It seems that:
#
#  a) To get the intel accelerated drivers, we need DRI,
#  b) DRI requires big OpenGL,
#  c) Big GL can't be built without GLX; libGL will never linked if
#     --disable-glx is passed
#  d) DRI/libdrm also have X11 deps, though we have lost that battle
#     already

require ${COREBASE}/meta/recipes-graphics/mesa/mesa-dri_${PV}.bb

PR_append = ".1"

#PROTO_DEPS = "glproto dri2proto"
#LIB_DEPS = "libxml2-native"

DEPENDS += "libdrm udev"

COMPATIBLE_MACHINE = "atom-egl"
DEFAULT_PREFERENCE_atom-egl = "100"

PROVIDES += "virtual/egl virtual/libgles2"

DRI_DRIVERS=""
GALLIUM_DRIVERS=""

DRI_DRIVERS_atom-egl = "i915,i965"
GALLIMU_DRIVERS_atom-nvidia = "noveau"

# have to use = here, because the Yocto recipe uses the deprecated option
# --with-driver which fails if some other options are not in their auto
# state
EXTRA_OECONF = "\
		--disable-xorg					\
		--disable-xa					\
		--disable-d3d1x					\
		--disable-xlib-glx				\
	        --disable-glu					\
                --disable-glw					\
                --disable-glut					\
		--disable-gallium-egl				\
		--with-gallium-drivers=${GALLIUM_DRIVERS}	\
		--disable-gallium-egl				\
		--enable-shared-glapi				\
		--enable-gbm					\
		--enable-opengl					\
		--disable-gles1					\
		--enable-gles2					\
		--enable-egl					\
		--with-egl-platforms=drm			\
		--enable-dri					\
		--with-dri-drivers=${DRI_DRIVERS}		\
		"

PACKAGES =+ "libgbm libgbm-dev libgbm-dbg libgles2 libgles2-dev libgles2-dbg"

FILES_libgbm = "${libdir}/libgbm.so.*"
FILES_libgbm-dev = "${libdir}/libgbm* ${libdir}/pkgconfig/gbm.pc ${includedir}/gbm.h"
FILES_libgbm-dbg += "${libdir}/gbm/.debug/libgbm*"

FILES_libgles2 = "${libdir}/libGLESv2.so.*"
FILES_libgles2-dev = "${libdir}/libGLESv2.* ${includedir}/GLES2 ${libdir}/pkgconfig/glesv2.pc"
FILES_libgles2-dbg += "${libdir}/.debug/libGLESv2*"
