DEFAULT_PREFERENCE = "10"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS += "orc orc-native"

PRINC = "11"

SRC_URI += "file://pulse.conf \
	    file://config.patch \
	    file://fix-desktop.patch \
	   "

EXTRA_OECONF += "--enable-orc"

do_install_append() {
    rm ${D}${sysconfdir}/xdg/autostart/pulseaudio-kde.desktop
    install -d ${D}${datadir}/alsa/alsa.conf.d
    install -m 0755 ${WORKDIR}/pulse.conf ${D}${datadir}/alsa/alsa.conf.d/

}

FILES_${PN} += "${datadir}/alsa"

RDEPENDS_pulseaudio-server += "pulseaudio-module-switch-on-connect	\
			       pulseaudio-module-cli			\
			       pulseaudio-module-cli-protocol-unix	\
			       pulseaudio-module-mmkbd-evdev		\
			       pulseaudio-module-volume-restore		\
			       pulseaudio-module-rescue-streams		\
			      "
