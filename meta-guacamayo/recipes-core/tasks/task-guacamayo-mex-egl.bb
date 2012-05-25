DESCRIPTION="Guacamayo task for MEX"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r1"

DEPENDS += "alsa-plugins clutter-1.10-egl clutter-gst-1.10-egl"

PACKAGES="\
	task-guacamayo-mex-egl \
	task-guacamayo-mex-egl-tests \
	"

ALLOW_EMPTY = "1"

RDEPENDS_task-guacamayo-mex-egl = "			\
			      tracker			\
			      clutter-1.10-egl		\
			      clutter-gst-1.10-egl	\
			      media-explorer		\
			      guacamayo-watchdog	\
			      guacamayo-session-egl	\
			      "

RDEPENDS_task-guacamayo-mex-egl_append_beagleboard = " \
					   libgles-omap3-pvrini-native"

RDEPENDS_task-guacamayo-mex-egl-tests = "			\
				 clutter-1.10-egl-examples	\
				 clutter-gst-1.10-egl-examples	\
				 "