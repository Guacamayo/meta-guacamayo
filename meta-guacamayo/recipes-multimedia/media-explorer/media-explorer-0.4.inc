include media-explorer.inc

do_install_append() {
    install -d ${D}${sysconfdir}/xdg/autostart
    install -m 0544 ${D}${datadir}/applications/media-explorer.desktop ${D}${sysconfdir}/xdg/autostart

    rm ${D}${libdir}/media-explorer/plugins/*.la

    # Remove unused empty directories
    rmdir ${D}${datadir}/media-explorer/plugins/*
    rmdir ${D}${datadir}/media-explorer/plugins
    rmdir ${D}${datadir}/gir-1.0
    rmdir ${D}${libdir}/girepository-1.0
}

