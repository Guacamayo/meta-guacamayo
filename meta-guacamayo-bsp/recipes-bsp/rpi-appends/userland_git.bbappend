FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC = "2"

PROVIDES += "libopenmaxil"
RPROVIDES += "libopenmaxil libgles2"

SRC_URI += "file://egl.pc \
	    file://vchiq.sh"

prefix="/usr"

do_configure_prepend () {
    sed -i "s/\/opt\/vc/\/usr/" ${S}/makefiles/cmake/vmcs.cmake
}

do_install_append () {
    rm -rf ${D}/etc/ld.so.conf.d

  install -d ${D}${libdir}/pkgconfig
  install -m 0644 ${WORKDIR}/egl.pc ${D}${libdir}/pkgconfig/

  install -d ${D}/${sysconfdir}/init.d
  install -m 0755 ${WORKDIR}/vchiq.sh ${D}${sysconfdir}/init.d/

  install -d ${D}/${includedir}/interface/vmcs_host/
  install -m 0755 ${S}/interface/vmcs_host/*.h ${D}${includedir}/interface/vmcs_host

  install -d ${D}/${includedir}/interface/vctypes/
  install -m 0755 ${S}/interface/vctypes/*.h ${D}${includedir}/interface/vctypes

  install -d ${D}/${includedir}/interface/vchi/
  install -m 0755 ${S}/interface/vchi/*.h ${D}${includedir}/interface/vchi

  install -d ${D}/${includedir}/interface/vchi/connections/
  install -m 0755 ${S}/interface/vchi/connections/*.h ${D}${includedir}/interface/vchi/connections

  install -d ${D}/${includedir}/interface/vchi/message_drivers/
  install -m 0755 ${S}/interface/vchi/message_drivers/*.h ${D}${includedir}/interface/vchi/message_drivers
}

FILES_${PN} = "${libdir}/*.so ${sbindir} ${bindir} ${sysconfdir}"
FILES_${PN}-dbg += "${libdir}/.debug ${sbindir}/.debug ${bindir}/.debug"
FILES_${PN}-dev = "${includedir} ${libdir}/pkgconfig"
FILES_${PN}-staticdev = "${libdir}/*.a"
FILES_${PN}-doc = "${datadir}"