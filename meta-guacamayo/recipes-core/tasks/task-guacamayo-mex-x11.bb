DESCRIPTION="Guacamayo task for MEX"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r1"

PACKAGES="\
	task-guacamayo-mex-x11 \
	task-guacamayo-mex-x11-tests \
	"

ALLOW_EMPTY = "1"

# xserver-common, x11-common
VIRTUAL-RUNTIME_xserver_common ?= "x11-common"

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
	  "

RDEPENDS_task-guacamayo-mex-x11 = "		\
			      ${GUACA_X11}	\
			      clutter-1.8	\
			      clutter-gst-1.8	\
			      "

RDEPENDS_task-guacamayo-mex-x11-tests = "			\
				 clutter-1.8-examples		\
				 clutter-gst-1.8-examples	\
				 "