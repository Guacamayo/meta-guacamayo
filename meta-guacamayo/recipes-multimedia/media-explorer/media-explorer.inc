DESCRIPTION = "A media centre application for Linux"
SECTION = "x11/multimedia"
LICENSE = "LGPLv2.1"

DEPENDS = "gnome-common glib-2.0 libsoup-2.4 librest gdk-pixbuf grilo \
           clutter-gst-1.10 mx xtst gnome-desktop gssdp connman tracker tracker-upnp lirc"

inherit autotools gettext gtk-doc

SRC_URI = "http://github.com/downloads/media-explorer/media-explorer/${BPN}-${PV}.tar.xz\
	   file://0001-mex-lirc-remove-dependency-on-ClutterX11.patch"

INCPR = "r8"

# Cheating, disabling web remote as it needs tracker-sparql-0.10 avahi-glib avahi-client
# Also disable debug buddy as it needs libunwind
EXTRA_OECONF += "--with-online=always --enable-networks --disable-webremote \
                 --enable-lirc --disable-debug-buddy"

python populate_packages_prepend() {
        plugindir = d.expand('${libdir}/media-explorer/plugins')
        do_split_packages(d, plugindir, '^mex-(.*)\.so$', '${PN}-plugin-%s', 'Media Explorer plugin for %s', allow_dirs=True, prepend=True, extra_depends='${PN}')

	pn = d.getVar('PN', True)
	metapkg =  pn + '-plugins-meta'
	d.setVar('ALLOW_EMPTY_' + metapkg, "1")
	d.setVar('FILES_' + metapkg, "")
	metapkg_rdepends = []
	packages = d.getVar('PACKAGES', True).split()
	for pkg in packages:
		if pkg.startswith(pn + '-plugin-') and not pkg in metapkg_rdepends and not pkg.endswith('-dev') and not pkg.endswith('-dbg') and not pkg.count('locale') and not pkg.count('-staticdev'):
			bb.note ("adding %s to meta rdepends" % pkg)
			metapkg_rdepends.append(pkg)
	d.setVar('RDEPENDS_' + metapkg, ' '.join(metapkg_rdepends))
	d.setVar('DESCRIPTION_' + metapkg, pn + ' meta package')
}

PACKAGES_DYNAMIC = "${PN}-plugin-*"

PACKAGES_prepend = "${PN}-autostart "
PACKAGES += "${PN}-plugins-meta"

FILES_${PN} += "${datadir}/icons/hicolor/*/apps/media-explorer.png \
                ${datadir}/dbus-1/services/*.service"
FILES_${PN}-dbg += "${libdir}/media-explorer/plugins/.debug"
FILES_${PN}-autostart = "${sysconfdir}/xdg/autostart"

RDEPENDS_${PN} += "ttf-droid grilo-plugins connman gst-meta-base"
RDEPENDS_${PN}-plugin-tracker += "tracker tracker-upnp"
RDEPENDS_${PN}-autostart = "${PN}"

