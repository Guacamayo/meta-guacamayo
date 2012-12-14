DEFAULT_PREFERENCE = "-1"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Depends from upstream, remove consolekit removed
DEPENDS = "libatomics-ops liboil avahi libsamplerate0 libsndfile1 libtool \
           ${@base_contains('DISTRO_FEATURES', 'x11', 'virtual/libx11 libxtst libice libsm libxcb gtk+', '', d)}"
DEPENDS += "udev alsa-lib glib-2.0 dbus \
           ${@base_contains('DISTRO_FEATURES', 'bluetooth', 'bluez4', '', d)}"
DEPENDS += "libjson gdbm speex libxml-parser-perl-native"


DEPENDS += "orc orc-native"

PRINC = "14"

SRC_URI += "file://pulse.conf \
	    file://config.patch \
	    file://fix-desktop.patch \
	   "

EXTRA_OECONF += "--enable-orc"

do_install_append() {
    rm -f ${D}${sysconfdir}/xdg/autostart/pulseaudio-kde.desktop
    install -d ${D}${datadir}/alsa/alsa.conf.d
    install -m 0755 ${WORKDIR}/pulse.conf ${D}${datadir}/alsa/alsa.conf.d/

}

FILES_${PN} += "${datadir}/alsa"

# From oe-core, minus the ConsoleKit integration as we don't need it
RDEPENDS_pulseaudio-server = " \
    pulseaudio-module-filter-apply \
    pulseaudio-module-filter-heuristics \
    pulseaudio-module-udev-detect \
    pulseaudio-module-null-sink \
    pulseaudio-module-device-restore \
    pulseaudio-module-stream-restore \
    pulseaudio-module-card-restore \
    pulseaudio-module-augment-properties \
    pulseaudio-module-detect \
    pulseaudio-module-alsa-sink \
    pulseaudio-module-alsa-source \
    pulseaudio-module-alsa-card \
    pulseaudio-module-native-protocol-unix \
    pulseaudio-module-default-device-restore \
    pulseaudio-module-intended-roles \
    pulseaudio-module-rescue-streams \
    pulseaudio-module-always-sink \
    pulseaudio-module-suspend-on-idle \
    pulseaudio-module-position-event-sounds \
    pulseaudio-module-role-cork"

RDEPENDS_pulseaudio-server += " \
    pulseaudio-module-switch-on-connect	\
    pulseaudio-module-switch-on-port-available	\
    pulseaudio-module-cli \
    pulseaudio-module-cli-protocol-unix \
    pulseaudio-module-mmkbd-evdev \
    pulseaudio-module-volume-restore \
    pulseaudio-module-rescue-streams"
