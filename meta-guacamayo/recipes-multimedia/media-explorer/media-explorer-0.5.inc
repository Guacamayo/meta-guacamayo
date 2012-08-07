include media-explorer.inc

inherit gsettings

do_install_append() {
    install -d ${D}${sysconfdir}/xdg/autostart
    install -m 0544 ${D}${datadir}/applications/media-explorer.desktop ${D}${sysconfdir}/xdg/autostart

    rm ${D}${libdir}/media-explorer/plugins/*.la

    # Remove unused empty directories
    rmdir ${D}${datadir}/media-explorer/plugins/*
    rmdir ${D}${datadir}/media-explorer/plugins
}



