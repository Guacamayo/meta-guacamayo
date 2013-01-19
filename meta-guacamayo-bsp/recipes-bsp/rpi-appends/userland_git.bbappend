
PRINC = "1"

PROVIDES += "libopenmaxil"
RPROVIDES += "libopenmaxil libgles2"

prefix="/usr"

do_configure_prepend () {
    sed -i "s/\/opt\/vc/\/usr/" ${S}/makefiles/cmake/vmcs.cmake
}

do_install_append () {
    rm -rf ${D}/etc/ld.so.conf.d
}

FILES_${PN} = "${libdir}/*.so ${sbindir} ${bindir} ${sysconfdir}"
FILES_${PN}-dbg += "${libdir}/.debug ${sbindir}/.debug ${bindir}/.debug"
FILES_${PN}-dev = "${includedir}"
FILES_${PN}-staticdev = "${libdir}/*.a"
FILES_${PN}-doc = "${datadir}"