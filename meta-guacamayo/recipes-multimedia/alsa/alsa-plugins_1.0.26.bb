DESCRIPTION = "ALSA Plugins"
HOMEPAGE = "http://www.alsa-project.org"
SECTION = "multimedia/alsa/plugins"
LICENSE = "LGPL2.1"
DEPENDS = "alsa-lib pulseaudio"
PR = "r1"

LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"
SRC_URI = "ftp://ftp.alsa-project.org/pub/plugins/alsa-plugins-${PV}.tar.bz2 \
	   file://use_libavutil.patch \
          "
SRC_URI[md5sum] = "4facd408326ef5567a7d4ceb6589e6b0"
SRC_URI[sha256sum] = "03515134d2009db4dfb2769e0ab0e1fb517c8140ffdfd64a984be968e81c9f1f"

inherit autotools

PACKAGES_DYNAMIC = "libasound-module*"

python populate_packages_prepend() {
    plugindir = bb.data.expand('${libdir}/alsa-lib/', d)
    do_split_packages(d, plugindir, '^libasound_module_(.*)\.so$', 'libasound-module-%s', 'Alsa plugin for %s', extra_depends='' )
}

FILES_${PN}-dev += "${libdir}/alsa-lib/libasound*.a ${libdir}/alsa-lib/libasound*.la"
FILES_${PN}-dbg += "${libdir}/alsa-lib/.debug"

FILES_libasound-module-conf-pulse += "${datadir}/alsa/alsa.conf.d/*pulseaudio*"
FILES_libasound-module-rate-samplerate += "${libdir}/alsa-lib/*rate_samplerate_*.so"
FILES_libasound-module-rate-speexrate += "${libdir}/alsa-lib/*rate_speexrate_*.so"

# Symlinks to .so files cause a warning, but these are legitimate.
INSANE_SKIP_libasound-module-rate-samplerate = "dev-so"
INSANE_SKIP_libasound-module-rate-speexrate = "dev-so"
