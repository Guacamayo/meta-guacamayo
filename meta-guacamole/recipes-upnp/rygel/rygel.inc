LICENSE = "LGPLv2+"

INC_PR = "rgc2"

inherit gnome update-rc.d useradd

SRC_URI = "${GNOME_MIRROR}/${BPN}/${@gnome_verdir("${PV}")}/${BPN}-${PV}.tar.xz;name=archive"

SRC_URI += "file://rygel"

DESCRIPTION = "Collection of DLNA[1] (UPnP[2] AV) devices, implemented through a plug-in mechanism."
SECTION = "network/multimedia"
DEPENDS = "glib-2.0 gupnp gupnp-av gupnp-dlna gstreamer sqlite3 libsoup-2.4 libgee"
HOMEPAGE = "http://live.gnome.org/Rygel"

INITSCRIPT_NAME = "rygel"

# need dbus, so start after and stop before
INITSCRIPT_PARAMS = "start 50 5 2 3 . stop 10 0 1 6 ."

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--home-dir=/var/run/rygel \
		       --create-home \
                       --groups video,audio \
                       --user-group rygel"
do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/rygel ${D}${sysconfdir}/init.d
}

FILES_${PN} += "${libdir}/rygel-1.0/librygel*.so \
                ${datadir}/dbus-1/ \
		${sysconfdir}/init.d \
	       "
FILES_${PN}-dbg += "${libdir}/rygel-1.0/.debug/"
