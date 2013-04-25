PACKAGE_ARCH = "${MACHINE_ARCH}"

# This recipe attempts to build mesa as a pure EGL/GLES2 platform, and though
# mesa has the configure parameters for that, it seems fairly clear that
# nobody has ever done that before, at least not with the DRI drivers
#
# Also, the Yocto mesa recipe is set up in a way that makes 'subclassing'
# it hard.

require ${COREBASE}/meta/recipes-graphics/mesa/mesa_${PV}.bb

PR_append = ".1"

DEPENDS += "libdrm udev"

COMPATIBLE_MACHINE = "(atom-egl|nuc)"
DEFAULT_PREFERENCE_atom-egl = "100"
DEFAULT_PREFERENCE_nuc = "100"

PROVIDES += "virtual/egl virtual/libgles2"

SRC_URI += "file://fix-gles-only-install.patch"

DRI_DRIVERS=""
GALLIUM_DRIVERS=""

DRI_DRIVERS_atom-egl = "i915,i965"
DRI_DRIVERS_nuc = "i965"
GALLIMU_DRIVERS_atom-nvidia = "noveau"

# blow this out of the way, it makes it completely impossible to tweak
# the configure parameters, because it stupidly appends itself to the
# end of the line
PACKAGECONFIG=""
PACKAGECONFIG[egl]=""
PACKAGECONFIG[x11]=""
PACKAGECONFIG[gles]=""

# dont's try to tweak the existing line, make our own!
EXTRA_OECONF = "\
		--disable-xorg					\
		--disable-xa					\
		--disable-d3d1x					\
		--disable-xlib-glx				\
		--disable-opengl				\
		--disable-glx					\
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
