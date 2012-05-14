DESCRIPTION = "libGLES for the omap3"
LICENSE = "proprietary-binary"
# 'TSPA.txt' might not be the best file to md5sum
LIC_FILES_CHKSUM = "file://TSPA.txt;md5=c0d5d9c1e38b41677144c4e24d6ddee1"

PR = "r26"

COMPATIBLE_MACHINE = "(omap3|ti816x|ti33x)"

DEPENDS = "virtual/libx11 libxau libxdmcp"

PROVIDES += "virtual/egl"

SRC_URI = "http://software-dl.ti.com/dsps/dsps_public_sw/sdo_sb/targetcontent/gfxsdk/${SGXPV}/exports/OMAP35x_Graphics_SDK_setuplinux_${SGXPV}.bin \
		   file://cputype \
		   file://rc.pvr \
		   file://sample.desktop \
		   file://99-bufferclass.rules  \
"

S = "${WORKDIR}/OMAP35x_Graphics_SDK_${SGXPV}"

# Logic to unpack installjammer file
TI_BIN_UNPK_CMDS="Y: qY:workdir:Y"
require ${GUACABASE}/meta-ti/recipes-ti/includes/ti-eula-unpack.inc

BINLOCATION ?= "${S}/gfx_rel"
ES2LOCATION ?= "${S}/gfx_rel_es2.x"
ES3LOCATION ?= "${S}/gfx_rel_es3.x"
ES5LOCATION ?= "${S}/gfx_rel_es5.x"
ES6LOCATION ?= "${S}/gfx_rel_es6.x"
ES8LOCATION ?= "${S}/gfx_rel_es8.x"

do_configure() {
	# Attempt to fix up the worst offenders for file permissions
	for i in $(find ${S} -name "*.h") $(find ${S} -name "*.c") $(find ${S} -name "Make*") ; do
		chmod 0644 $i
	done

	# Attempt to create proper library softlinks
	for sofile in $(find ${S} -name "lib*Open*.so") $(find ${S} -name "lib*srv*.so") $(find ${S} -name "lib*gl*.so") $(find ${S} -name "libpvr*.so") $(find ${S} -name "lib*GL*.so"); do
		if [ "$(readlink -n ${sofile})" = "" ] ; then
			mv $sofile ${sofile}.${IMGPV}
			ln -sf $(basename ${sofile}.${IMGPV}) ${sofile}
			ln -sf $(basename ${sofile}.${IMGPV}) ${sofile}$(echo ${IMGPV} | awk -F. '{print "." $1}')
			ln -sf $(basename ${sofile}.${IMGPV}) ${sofile}$(echo ${IMGPV} | awk -F. '{print "." $1 "." $2}')
		fi
	done

	# Due to recursive make PLAT_* isn't always passed down correctly, so use sed to fix those
	for mak in $(find ${S} -name "*.mak") ; do
		sed -i -e s:arm-none-linux-gnueabi-:${TARGET_PREFIX}:g $mak
	done

	# clear out old stuff
	find Binaries/ | xargs rm -f || true
}

# Force in GNU_HASH and paths to libs
TARGET_CC_ARCH += " ${TARGET_LINK_HASH_STYLE} -Wl,-rpath-link,${BINLOCATION} -L${BINLOCATION} \
-L${STAGING_DIR_TARGET}${libdir} -Wl,-rpath-link,${STAGING_DIR_TARGET}${libdir}"
PARALLEL_MAKE = ""

do_compile() {
	export X11ROOT="${STAGING_DIR_HOST}/usr"
	export TOOLCHAIN="${TOOLCHAIN_PATH}"
	export PLATFORM="LinuxOMAP3"

	export PLAT_CC="${CC}"
	export PLAT_CPP="${CXX}"
	export PLAR_AR="${AR}"

	mkdir -p ${S}/demos/raw
	mkdir -p ${S}/demos/x11

	# Rebuild demos for both Raw and X11
	for X11BUILD in 0 1 ; do
		for demo in ${S}/GFX_Linux_SDK/OGLES/SDKPackage/Demos/* ; do
			cd $demo/OGLES/Build/LinuxGeneric
			oe_runmake Common=1 PLATFORM=$PLATFORM X11BUILD=$X11BUILD
			rm $demo/OGLES/Build/LinuxOMAP3/Release*/*.o
			install -m 0755 $demo/OGLES/Build/LinuxOMAP3/ReleaseX11/* ${S}/demos/x11 || true
			sed -e s:NAME:$(basename $demo): \
			    -e s:EXEC:${bindir}/SGX/demos/X11/$(basename $demo/OGLES/Build/LinuxOMAP3/ReleaseX11/*): \
			     ${WORKDIR}/sample.desktop > ${WORKDIR}/$(basename $demo).desktop
			install -m 0755 $demo/OGLES/Build/LinuxOMAP3/ReleaseRaw/* ${S}/demos/raw || true
		done
		for demo in ${S}/GFX_Linux_SDK/OGLES2/SDKPackage/Demos/* ; do
			cd $demo/OGLES2/Build/LinuxGeneric
			oe_runmake Common=1 PLATFORM=$PLATFORM X11BUILD=$X11BUILD
			rm -f $demo/OGLES2/Build/LinuxOMAP3/Release*/*.o
			install -m 0755 $demo/OGLES2/Build/LinuxOMAP3/ReleaseX11/* ${S}/demos/x11 || true
			sed -e s:NAME:$(basename $demo): \
			    -e s:EXEC:${bindir}/SGX/demos/X11/$(basename $demo/OGLES2/Build/LinuxOMAP3/ReleaseX11/*): \
			     ${WORKDIR}/sample.desktop > ${WORKDIR}/$(basename $demo).desktop
			install -m 0755 $demo/OGLES2/Build/LinuxOMAP3/ReleaseRaw/* ${S}/demos/raw || true
		done
	done

	mkdir -p ${S}/trainingcourses/raw
	mkdir -p ${S}/trainingcourses/x11

	find ${S} -name "*_org" -delete

	# Build OGLES2 Trainingcourses for both Raw and X11
	for X11BUILD in 0 1 ; do
		for training in ${S}/GFX_Linux_SDK/OGLES2/SDKPackage/TrainingCourse/* ; do
			if [ -e $training/OGLES2/Build/LinuxGeneric/Makefile ] ; then
				cd $training/OGLES2/Build/LinuxGeneric
			fi

			if [ -e $training/OGLES2/Build/LinuxOMAP3/Makefile ] ; then
				cd $training/OGLES2/Build/LinuxOMAP3
			fi

			oe_runmake Common=1 PLATFORM=$PLATFORM X11BUILD=$X11BUILD
			rm -f $training/OGLES2/Build/LinuxOMAP3/Release*/*.o
			install -m 0755 $training/OGLES2/Build/LinuxOMAP3/ReleaseX11/* ${S}/trainingcourses/x11 || true
			install -m 0755 $training/OGLES2/Build/LinuxOMAP3/ReleaseRaw/* ${S}/trainingcourses/raw || true
		done
	done

}

do_install () {
	install -d ${D}${libdir}
	cp -pPR ${BINLOCATION}/*.so* ${D}${libdir}
	install -m 0644 ${BINLOCATION}/*.a ${D}${libdir}

	install -d ${D}${bindir}/
	install -m 0755 ${WORKDIR}/cputype ${D}${bindir}/

	install -m 0755 ${BINLOCATION}/*_test ${D}${bindir}/
	install -m 0755 ${BINLOCATION}/gl* ${D}${bindir}/
	install -m 0755 ${BINLOCATION}/p[dv]* ${D}${bindir}/
	install -m 0755 ${BINLOCATION}/xgles1test1 ${D}${bindir}/

	install -m 0755 ${BINLOCATION}/freedesktop/kdrive/usr/X11R6_SGX/bin/Xsgx ${D}${bindir}/|| true
	cp -pPR ${S}/GFX_Linux_SDK/OGLES2/SDKPackage/Builds/OGLES2/LinuxOMAP3/lib/*X11WS* ${D}${libdir} || true

	install -d ${D}${includedir}
	cp -pPR ${S}/GFX_Linux_KM/include4 ${D}${includedir}/
	cp -pPR ${S}/GFX_Linux_KM/services4 ${D}${includedir}/

	cp -pPR ${S}/GFX_Linux_SDK/OGLES2/SDKPackage/Builds/OGLES2/Include/* ${D}${includedir}/
	cp -pPR ${S}/GFX_Linux_SDK/OGLES/SDKPackage/Builds/OGLES/Include/* ${D}${includedir}/
	cp -pPR ${S}/GFX_Linux_SDK/OGLES/SDKPackage/Builds/OGLES/LinuxOMAP3/Include/GLES/* ${D}${includedir}/GLES/ || true
	cp -pPr ${S}/GFX_Linux_SDK/OGLES2/SDKPackage/Builds/OGLES2/LinuxOMAP3/Include/GLES/* ${D}${includedir}/GLES2/ || true
	cp -pPr ${S}/GFX_Linux_SDK/OVG/SDKPackage/Builds/OVG/Include/v* ${D}${includedir}/ || true
	cp -pPr ${S}/GFX_Linux_SDK/OVG/SDKPackage/Builds/OVG/Include/V* ${D}${includedir}/ || true
	cp -pPr ${S}/include/*.h ${D}${includedir} || true
	cp -pPr ${S}/include/wsegl/*.h ${D}${includedir} || true

	install -d ${D}${sysconfdir}/init.d/
	cp -pP ${WORKDIR}/rc.pvr ${D}${sysconfdir}/init.d/pvr-init

	install -d ${D}${sysconfdir}
	echo "[default]" > ${D}${sysconfdir}/powervr.ini
	echo "WindowSystem=libpvrPVR2D_FRONTWSEGL.so.1" >> ${D}${sysconfdir}/powervr.ini

	# The ES2.x, ES3.x, ES5.x and ES6.x CPUs have different SGX hardware, so we need to install multiple sets of userspace

	install -d ${D}${libdir}/ES8.0
	install -d ${D}${libdir}/ES6.0
	install -d ${D}${libdir}/ES5.0
	install -d ${D}${libdir}/ES3.0
	install -d ${D}${libdir}/ES2.0

	install -d ${D}${bindir}/ES8.0
	install -d ${D}${bindir}/ES6.0
	install -d ${D}${bindir}/ES5.0
	install -d ${D}${bindir}/ES3.0
	install -d ${D}${bindir}/ES2.0


	if [ -e ${ES2LOCATION} ] ; then
		cp -pPR ${ES2LOCATION}/lib*${IMGPV} ${D}${libdir}/ES2.0/
		cp ${ES2LOCATION}/p[dv]* ${D}${bindir}/ES2.0/
	fi

	if [ -e ${ES3LOCATION} ] ; then
		cp -pPR ${ES3LOCATION}/lib*${IMGPV} ${D}${libdir}/ES3.0/
		cp ${ES3LOCATION}/p[dv]* ${D}${bindir}/ES3.0
	fi

	if [ -e ${ES5LOCATION} ] ; then
		cp -pPR ${ES5LOCATION}/lib*${IMGPV} ${D}${libdir}/ES5.0/
		cp ${ES5LOCATION}/p[dv]* ${D}${bindir}/ES5.0/
	fi

	if [ -e ${ES6LOCATION} ] ; then
		cp -pPR ${ES6LOCATION}/lib*${IMGPV} ${D}${libdir}/ES6.0/
		cp ${ES6LOCATION}/p[dv]* ${D}${bindir}/ES6.0/
	fi

	if [ -e ${ES8LOCATION} ] ; then
		cp -pPR ${ES8LOCATION}/lib*${IMGPV} ${D}${libdir}/ES8.0/
		cp ${ES8LOCATION}/p[dv]* ${D}${bindir}/ES8.0/
	fi

	rm ${D}${bindir}/ES*/*.h ${D}${bindir}/ES*/pdsasm ${D}${bindir}/pdsasm -f || true

	install -d ${D}${prefix}/share/applications
	cp ${WORKDIR}/*.desktop ${D}${prefix}/share/applications
	rm ${D}${prefix}/share/applications/sample.desktop

	install -d ${D}${bindir}/SGX/demos/X11/
	install -d ${D}${bindir}/SGX/demos/Raw/
	install -m 0755 ${S}/demos/x11/* ${D}${bindir}/SGX/demos/X11/
	install -m 0755 ${S}/demos/raw/* ${D}${bindir}/SGX/demos/Raw/

	install -d ${D}${bindir}/SGX/trainingcourses/Raw
	install -d ${D}${bindir}/SGX/trainingcourses/X11
	install -m 0755 ${S}/trainingcourses/x11/* ${D}${bindir}/SGX/trainingcourses/X11/
	install -m 0755 ${S}/trainingcourses/raw/* ${D}${bindir}/SGX/trainingcourses/Raw/

	# Delete objects and linker scripts hidden between the headers
	find ${D} -name "*.o" -delete
	find ${D} -name "*.o.cmd" -delete

	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/99-bufferclass.rules ${D}${sysconfdir}/udev/rules.d/

}

PACKAGES =+ "${PN}-rawdemos \
             ${PN}-x11demos \
             ${PN}-rawtrainingcourses \
             ${PN}-x11trainingcourses \
"

PACKAGES += "xserver-kdrive-powervrsgx \
             ${PN}-tests "

# Package the base libraries per silicon revision
PACKAGES =+ "${PN}-es2 ${PN}-es3 ${PN}-es5 ${PN}-es6 ${PN}-es8"
RRECOMMENDS_${PN} += "${PN}-es2 ${PN}-es3 ${PN}-es5 ${PN}-es6 ${PN}-es8"
FILES_${PN}-es2 = "${libdir}/ES2*/* ${bindir}/ES2*/*"
FILES_${PN}-es3 = "${libdir}/ES3*/* ${bindir}/ES3*/*"
FILES_${PN}-es5 = "${libdir}/ES5*/* ${bindir}/ES5*/*"
FILES_${PN}-es6 = "${libdir}/ES6*/* ${bindir}/ES6*/*"
FILES_${PN}-es8 = "${libdir}/ES8*/* ${bindir}/ES8*/*"

# Stop shlib code from picking a subpackage
PRIVATE_LIBS_${PN}-es2 = "libGLESv2.so libIMGegl.so libsrv_um.so libOpenVG.so libpvr2d.so libsrv_init.so libEGL.so libsrv_um_dri.so libOpenVGU.so libglslcompiler.so libGLES_CM.so"
PRIVATE_LIBS_${PN}-es3 = "libGLESv2.so libIMGegl.so libsrv_um.so libOpenVG.so libpvr2d.so libsrv_init.so libEGL.so libsrv_um_dri.so libOpenVGU.so libglslcompiler.so libGLES_CM.so"
PRIVATE_LIBS_${PN}-es5 = "libGLESv2.so libIMGegl.so libsrv_um.so libOpenVG.so libpvr2d.so libsrv_init.so libEGL.so libsrv_um_dri.so libOpenVGU.so libglslcompiler.so libGLES_CM.so"
PRIVATE_LIBS_${PN}-es6 = "libGLESv2.so libIMGegl.so libsrv_um.so libOpenVG.so libpvr2d.so libsrv_init.so libEGL.so libsrv_um_dri.so libOpenVGU.so libglslcompiler.so libGLES_CM.so"
PRIVATE_LIBS_${PN}-es8 = "libGLESv2.so libIMGegl.so libsrv_um.so libOpenVG.so libpvr2d.so libsrv_init.so libEGL.so libsrv_um_dri.so libOpenVGU.so libglslcompiler.so libGLES_CM.so"

PACKAGES =+ "${PN}-blitwsegl ${PN}-flipwsegl ${PN}-frontwsegl ${PN}-linuxfbwsegl ${PN}-x11wsegl ${PN}-driwsegl"
FILES_${PN}-blitwsegl = "${libdir}/libpvrPVR2D_BLITWSEGL.so.*"
FILES_${PN}-flipwsegl = "${libdir}/libpvrPVR2D_FLIPWSEGL.so.*"
FILES_${PN}-frontwsegl = "${libdir}/libpvrPVR2D_FRONTWSEGL.so.*"
FILES_${PN}-linuxfbwsegl = "${libdir}/libpvrPVR2D_LINUXFBWSEGL.so.*"
FILES_${PN}-x11wsegl = "${libdir}/libpvrPVR2D_X11WSEGL.so*"
FILES_${PN}-driwsegl = "${libdir}/libpvrPVR2D_DRIWSEGL.so*"

PACKAGES =+ "${PN}-blitwsegl-es2 ${PN}-blitwsegl-es3 ${PN}-blitwsegl-es5 ${PN}-blitwsegl-es6 ${PN}-blitwsegl-es8 ${PN}-flipwsegl-es2 ${PN}-flipwsegl-es3 ${PN}-flipwsegl-es5 ${PN}-flipwsegl-es6 ${PN}-flipwsegl-es8 ${PN}-frontwsegl-es2 ${PN}-frontwsegl-es3 ${PN}-frontwsegl-es5 ${PN}-frontwsegl-es6 ${PN}-frontwsegl-es8 ${PN}-linuxfbwsegl-es2 ${PN}-linuxfbwsegl-es3 ${PN}-linuxfbwsegl-es5 ${PN}-linuxfbwsegl-es6 ${PN}-linuxfbwsegl-es8 ${PN}-x11wsegl-es2 ${PN}-x11wsegl-es3 ${PN}-x11wsegl-es5 ${PN}-x11wsegl-es6 ${PN}-x11wsegl-es8 ${PN}-driwsegl-es2 ${PN}-driwsegl-es3 ${PN}-driwsegl-es5 ${PN}-driwsegl-es6 ${PN}-driwsegl-es8"
FILES_${PN}-blitwsegl-es2 = "${libdir}/ES2*/libpvrPVR2D_BLITWSEGL.so.*"
FILES_${PN}-blitwsegl-es3 = "${libdir}/ES3*/libpvrPVR2D_BLITWSEGL.so.*"
FILES_${PN}-blitwsegl-es5 = "${libdir}/ES5*/libpvrPVR2D_BLITWSEGL.so.*"
FILES_${PN}-blitwsegl-es6 = "${libdir}/ES6*/libpvrPVR2D_BLITWSEGL.so.*"
FILES_${PN}-blitwsegl-es8 = "${libdir}/ES8*/libpvrPVR2D_BLITWSEGL.so.*"
RRECOMMENDS_${PN}-blitwsegl = " ${PN}-blitwsegl-es2 ${PN}-blitwsegl-es3 ${PN}-blitwsegl-es5 ${PN}-blitwsegl-es6 ${PN}-blitwsegl-es8"

FILES_${PN}-flipwsegl-es2 = "${libdir}/ES2*/libpvrPVR2D_FLIPWSEGL.so.*"
FILES_${PN}-flipwsegl-es3 = "${libdir}/ES3*/libpvrPVR2D_FLIPWSEGL.so.*"
FILES_${PN}-flipwsegl-es5 = "${libdir}/ES5*/libpvrPVR2D_FLIPWSEGL.so.*"
FILES_${PN}-flipwsegl-es6 = "${libdir}/ES6*/libpvrPVR2D_FLIPWSEGL.so.*"
FILES_${PN}-flipwsegl-es8 = "${libdir}/ES8*/libpvrPVR2D_FLIPWSEGL.so.*"
RRECOMMENDS_${PN}-flipwsegl = " ${PN}-flipwsegl-es2 ${PN}-flipwsegl-es3 ${PN}-flipwsegl-es5 ${PN}-flipwsegl-es6 ${PN}-flipwsegl-es8"

FILES_${PN}-frontwsegl-es2 = "${libdir}/ES2*/libpvrPVR2D_FRONTWSEGL.so.*"
FILES_${PN}-frontwsegl-es3 = "${libdir}/ES3*/libpvrPVR2D_FRONTWSEGL.so.*"
FILES_${PN}-frontwsegl-es5 = "${libdir}/ES5*/libpvrPVR2D_FRONTWSEGL.so.*"
FILES_${PN}-frontwsegl-es6 = "${libdir}/ES6*/libpvrPVR2D_FRONTWSEGL.so.*"
FILES_${PN}-frontwsegl-es8 = "${libdir}/ES8*/libpvrPVR2D_FRONTWSEGL.so.*"
RRECOMMENDS_${PN}-frontwsegl = " ${PN}-frontwsegl-es2 ${PN}-frontwsegl-es3 ${PN}-frontwsegl-es5 ${PN}-frontwsegl-es6 ${PN}-frontwsegl-es8"

FILES_${PN}-linuxfbwsegl-es2 = "${libdir}/ES2*/libpvrPVR2D_LINUXFBWSEGL.so.*"
FILES_${PN}-linuxfbwsegl-es3 = "${libdir}/ES3*/libpvrPVR2D_LINUXFBWSEGL.so.*"
FILES_${PN}-linuxfbwsegl-es5 = "${libdir}/ES5*/libpvrPVR2D_LINUXFBWSEGL.so.*"
FILES_${PN}-linuxfbwsegl-es6 = "${libdir}/ES6*/libpvrPVR2D_LINUXFBWSEGL.so.*"
FILES_${PN}-linuxfbwsegl-es8 = "${libdir}/ES8*/libpvrPVR2D_LINUXFBWSEGL.so.*"
RRECOMMENDS_${PN}-linuxfbwsegl = " ${PN}-linuxfbwsegl-es2 ${PN}-linuxfbwsegl-es3 ${PN}-linuxfbwsegl-es5 ${PN}-linuxfbwsegl-es6 ${PN}-linuxfbwsegl-es8"

FILES_${PN}-x11wsegl-es2 = "${libdir}/ES2*/libpvrPVR2D_X11WSEGL.so.*"
FILES_${PN}-x11wsegl-es3 = "${libdir}/ES3*/libpvrPVR2D_X11WSEGL.so.*"
FILES_${PN}-x11wsegl-es5 = "${libdir}/ES5*/libpvrPVR2D_X11WSEGL.so.*"
FILES_${PN}-x11wsegl-es6 = "${libdir}/ES6*/libpvrPVR2D_X11WSEGL.so.*"
FILES_${PN}-x11wsegl-es8 = "${libdir}/ES8*/libpvrPVR2D_X11WSEGL.so.*"
RRECOMMENDS_${PN}-x11wsegl = " ${PN}-x11wsegl-es2 ${PN}-x11wsegl-es3 ${PN}-x11wsegl-es5 ${PN}-x11wsegl-es6 ${PN}-x11wsegl-es8"

FILES_${PN}-driwsegl-es2 = "${libdir}/ES2*/libpvrPVR2D_DRIWSEGL.so.*"
FILES_${PN}-driwsegl-es3 = "${libdir}/ES3*/libpvrPVR2D_DRIWSEGL.so.*"
FILES_${PN}-driwsegl-es5 = "${libdir}/ES5*/libpvrPVR2D_DRIWSEGL.so.*"
FILES_${PN}-driwsegl-es6 = "${libdir}/ES6*/libpvrPVR2D_DRIWSEGL.so.*"
FILES_${PN}-driwsegl-es8 = "${libdir}/ES8*/libpvrPVR2D_DRIWSEGL.so.*"
RRECOMMENDS_${PN}-driwsegl = " ${PN}-driwsegl-es2 ${PN}-driwsegl-es3 ${PN}-driwsegl-es5 ${PN}-driwsegl-es6 ${PN}-driwsegl-es8"

CONFFILES_${PN} = "${sysconfdir}/powervr.ini"

FILES_${PN} = "${sysconfdir} ${libdir}/lib*.so.* ${libdir}/ES*/* ${bindir}/pvrsrvinit ${bindir}/cputype ${bindir}/*/*"

FILES_xserver-kdrive-powervrsgx = "${bindir}/Xsgx"
FILES_${PN}-tests = "${bindir}/*"
FILES_${PN}-dbg = "${libdir}/.debug/* ${bindir}/.debug/* \
                   ${libdir}/ES*/.debug ${bindir}/*/.debug\
                   ${bindir}/SGX/demos/*/.debug/* \
                   ${bindir}/SGX/trainingcourses/*/.debug/* \
	"

FILES_${PN}-rawdemos = "${bindir}/SGX/demos/Raw/*"
FILES_${PN}-x11demos = "${bindir}/SGX/demos/X11/* ${prefix}/share/applications "
RRECOMMENDS_${PN}-x11demos = "${PN}-x11wsegl"

FILES_${PN}-rawtrainingcourses = "${bindir}/SGX/trainingcourses/Raw/*"
FILES_${PN}-x11trainingcourses = "${bindir}/SGX/trainingcourses/X11/*"
RRECOMMENDS_${PN}-x11trainingcourses = "${PN}-x11wsegl"

# The libs need the kernel-modules
RRECOMMENDS_${PN} += "omap3-sgx-modules"

# The initscript calls fbset, cputype calls devmem2
RDEPENDS_${PN} += "fbset devmem2"

#HACK! These are binaries, so we can't guarantee that LDFLAGS match :(
INSANE_SKIP_${PN} = "ldflags dev-so"
INSANE_SKIP_${PN}-blitwsegl = "ldflags"
INSANE_SKIP_${PN}-flipwsegl = "ldflags"
INSANE_SKIP_${PN}-frontwsegl = "ldflags"
INSANE_SKIP_${PN}-linuxfbwsegl = "ldflags"
INSANE_SKIP_${PN}-x11wsegl = "ldflags dev-so"
INSANE_SKIP_${PN}-driwsegl = "ldflags dev-so"
INSANE_SKIP_${PN}-tests = "ldflags"
INSANE_SKIP_xserver-kdrive-powervrsgx = "ldflags"

# Quality control is really poor on these SDKs, so hack around the latest madness:
FILES_${PN} += "${libdir}/*.so "
FILES_${PN}-dev = "${includedir}"

inherit update-rc.d

INITSCRIPT_NAME = "pvr-init"
INITSCRIPT_PARAMS = "start 30 5 2 . stop 40 0 1 6 ."

# Append to update-rc.d postinst
pkg_postinst_${PN}_append() {
rm -f $D${sysconfdir}/powervr-esrev

ln -sf /usr/lib/libXdmcp.so.6.0.0 /usr/lib/libXdmcp.so.0
ln -sf /usr/lib/libXau.so.6.0.0 /usr/lib/libXau.so.0
}

pkg_postinst_${PN}-blitwsegl() {
rm -f $D${sysconfdir}/powervr-esrev
}

pkg_postinst_${PN}-flipwsegl() {
rm -f $D${sysconfdir}/powervr-esrev
}

pkg_postinst_${PN}-frontwsegl() {
rm -f $D${sysconfdir}/powervr-esrev
}
pkg_postinst_${PN}-linuxfbwsegl() {
rm -f $D${sysconfdir}/powervr-esrev
}

pkg_postinst_${PN}-x11wsegl() {
rm -f $D${sysconfdir}/powervr-esrev

echo "[default]" > $D${sysconfdir}/powervr.ini
echo "WindowSystem=libpvrPVR2D_X11WSEGL.so.1" >> $D${sysconfdir}/powervr.ini
}

pkg_postinst_${PN}-driwsegl() {
rm -f $D${sysconfdir}/powervr-esrev
}

