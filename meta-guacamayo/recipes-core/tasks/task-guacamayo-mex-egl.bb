DESCRIPTION="Guacamayo task for MEX"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r6"

DEPENDS += "alsa-plugins"

PACKAGES="\
	task-guacamayo-mex-egl		\
	task-guacamayo-mex-egl-tests	\
	task-guacamayo-mex-egl-dbg	\
	"

ALLOW_EMPTY = "1"

RDEPENDS_task-guacamayo-mex-egl = "				\
			      task-guacamayo-renderer		\
			      tracker				\
			      media-explorer			\
			      media-explorer-plugins-meta	\
			      guacamayo-watchdog		\
			      guacamayo-session-egl		\
			      guacamayo-mex-plugins		\
			      "

RDEPENDS_task-guacamayo-mex-egl-dbg = "				\
			      media-explorer-dbg		\
			      "

RDEPENDS_task-guacamayo-mex-egl_append_beagleboard = " \
					   libgles-omap3-pvrini-native"

RDEPENDS_task-guacamayo-mex-egl-tests = "			\
				 clutter-1.10-examples	\
				 clutter-gst-1.10-examples	\
				 "