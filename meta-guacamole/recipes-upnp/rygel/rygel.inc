LICENSE = "LGPLv2+"

INC_PR = "rgc7"

inherit gnome update-rc.d useradd

SRC_URI = "${GNOME_MIRROR}/${BPN}/${@gnome_verdir("${PV}")}/${BPN}-${PV}.tar.xz;name=archive"

SRC_URI += "file://rygel"

DESCRIPTION = "Collection of DLNA[1] (UPnP[2] AV) devices, implemented through a plug-in mechanism."
SECTION = "network/multimedia"
DEPENDS = "glib-2.0 gupnp gupnp-av gupnp-dlna gstreamer sqlite3 libsoup-2.4 libgee shared-mime-info"
RDEPENDS_${PN} = "sudo shared-mime-info-data pulseaudio-server"

require rygel-plugins.inc
PACKAGES_DYNAMIC = "${PN}-*"

PACKAGES =+ "${PN}-initd"

HOMEPAGE = "http://live.gnome.org/Rygel"

INITSCRIPT_PACKAGES = "${PN}-initd"
INITSCRIPT_NAME_${PN}-initd = "rygel"
# need dbus, so start after and stop before
INITSCRIPT_PARAMS_${PN}-initd = "start 50 5 2 3 . stop 10 0 1 6 ."

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--home-dir=/var/run/rygel \
		       --create-home \
                       --groups video,audio \
                       --user-group rygel"
do_install_append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/rygel ${D}${sysconfdir}/init.d
}

FILES_${PN} += "${datadir}/dbus-1/"
FILES_${PN}-dbg += "${libdir}/rygel-1.0/.debug/"
FILES_${PN}-initd += "${sysconfdir}/init.d"
