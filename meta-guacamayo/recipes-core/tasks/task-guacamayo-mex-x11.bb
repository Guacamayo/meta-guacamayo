DESCRIPTION="Guacamayo task for MEX"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r24"

PACKAGES="\
	task-guacamayo-mex-x11		\
	task-guacamayo-mex-x11-tests	\
	task-guacamayo-mex-x11-dbg	\
	"

DEPENDS += "alsa-plugins"

ALLOW_EMPTY = "1"

# xserver-common, x11-common
VIRTUAL-RUNTIME_xserver_common ?= "guacamayo-session-x11"

# elsa, xserver-nodm-init
VIRTUAL-RUNTIME_graphical_init_manager ?= "xserver-nodm-init"

GUACA_X11 = "						\
	  ${XSERVER}					\
    	  ${VIRTUAL-RUNTIME_xserver_common}		\
    	  ${VIRTUAL-RUNTIME_graphical_init_manager}	\
    	  liberation-fonts				\
    	  xauth						\
    	  xhost						\
    	  xset						\
    	  xrandr					\
	  xkbcomp					\
	  pulseaudio-module-x11-publish			\
	  "

#
# Video drivers, based on machine features
#
GUACA_X11_i915 = "xf86-video-intel	\
               	  mesa-dri-driver-i915	\
		 "

GUACA_X11_i965 = "xf86-video-intel	\
               	  mesa-dri-driver-i965	\
		 "

GUACA_X11_nvidia = "xf86-video-nvidia	\
		    libgl-nvidia	\
		   "

GUACA_X11 += "${@base_contains("MACHINE_FEATURES", "intel-gfx-i915", \
                      "${GUACA_X11_i915}", "", d)}"
GUACA_X11 += "${@base_contains("MACHINE_FEATURES", "intel-gfx-i965", \
                      "${GUACA_X11_i965}", "", d)}"
GUACA_X11 += "${@base_contains("MACHINE_FEATURES", "nvidia-gfx", \
                      "${GUACA_X11_nvidia}", "", d)}"


RDEPENDS_task-guacamayo-mex-x11 = "				\
			      task-guacamayo-renderer		\
			      ${GUACA_X11}			\
			      tracker				\
			      media-explorer			\
			      media-explorer-plugins-meta	\
			      guacamayo-watchdog-autostart	\
			      gst-plugins-base-meta		\
			      gst-plugins-good-meta		\
			      guacamayo-displayconf-autostart	\
			      guacamayo-mex-plugins		\
			      "

RDEPENDS_task-guacamayo-mex-x11-dbg = "				\
			      media-explorer-dbg		\
			      "

RDEPENDS_task-guacamayo-mex-x11_append_beagleboard = " \
					   libgles-omap3-pvrini-dri"

RDEPENDS_task-guacamayo-mex-x11-tests = "			\
				 clutter-1.12-examples		\
				 clutter-gst-1.6-examples	\
				 cogl-1.12-examples		\
				 mesa-demos			\
				 "