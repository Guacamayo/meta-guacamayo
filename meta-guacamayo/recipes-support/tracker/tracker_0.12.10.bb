DESCRIPTION = "Tracker is a tool designed to extract information and metadata about your personal data so that it can be searched easily and quickly."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=ee31012bf90e7b8c108c69f197f3e3a4"
DEPENDS = "file gtk+ gstreamer gamin dbus libexif gettext gmime sqlite3 icu"
RDEPENDS_${PN} += " gvfs"
HOMEPAGE = "http://projects.gnome.org/tracker/"

PR = "r0"

inherit autotools pkgconfig gnome gettext

VER_DIR = "${@gnome_verdir("${PV}")}"
SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/tracker/${VER_DIR}/tracker-${PV}.tar.xz \
           file://0005-Fix-missing-gobject-introspection-checks.patch \
           file://enable-sqlite-crosscompile.patch \
           file://90tracker \
" 

EXTRA_OECONF += " tracker_cv_have_ioprio=yes --disable-introspection"

LEAD_SONAME = "libtrackerclient.so.0"

do_install_append() {
   cp -PpR ${D}${STAGING_DATADIR}/* ${D}${datadir}/ || true
   install -d ${D}/${sysconfdir}/X11/Xsession.d/
   install -m 0755 ${WORKDIR}/90tracker  ${D}/${sysconfdir}/X11/Xsession.d/
   rmdir ${D}${libdir}/tracker-${VER_DIR}/writeback-modules
}

PACKAGES_prepend = "${PN}-tests ${PN}-vala "

FILES_${PN} += "${datadir}/dbus-1/ \
                ${libdir}/tracker-${VER_DIR}/*.so.* \
                ${libdir}/tracker-${VER_DIR}/extract-modules/*.so \
                ${datadir}/icons/hicolor/*/apps/tracker.* \
"
FILES_${PN}-dev += "${libdir}/tracker-${VER_DIR}/*.la \
                    ${libdir}/tracker-${VER_DIR}/*.so \
                    ${libdir}/tracker-${VER_DIR}/extract-modules/*.la"
FILES_${PN}-dbg += "${libdir}/*/*/.debug \
                    ${libdir}/*/.debug"
FILES_${PN}-tests = "${datadir}/tracker-tests/"
FILES_${PN}-vala = "${datadir}/vala/"

CONFFILES_${PN} += "${sysconfdir}/X11/Xsession.d/90tracker"

SRC_URI[md5sum] = "1ca82c48e79002df9f3a5487d5979513"
SRC_URI[sha256sum] = "42722831fdde63cfa07e4f0bb75805425c09b1f1801d3d5280de8be37a357b69"
