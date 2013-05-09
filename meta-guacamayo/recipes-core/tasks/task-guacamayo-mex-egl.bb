DESCRIPTION="Guacamayo task for MEX"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${THISDIR}/../../COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r10"

DEPENDS += "alsa-plugins"

PACKAGES="\
	task-guacamayo-mex-egl		\
	task-guacamayo-mex-egl-tests	\
	task-guacamayo-mex-egl-dbg	\
	"

python __anonymous () {
    # Set ALLOW_EMPTY on all packages
    packages = d.getVar('PACKAGES', True).split()
    for pkg in packages:
        d.setVar("ALLOW_EMPTY_%s" % pkg, "1")
}

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

RDEPENDS_task-guacamayo-mex-egl_append_nuc = "mesa-dri-driver-i965"

RDEPENDS_task-guacamayo-mex-egl-tests = "			\
				 clutter-1.12-examples		\
				 cogl-1.12-examples		\
				 clutter-gst-1.6-examples	\
				 "
