
PRINC = "1"

do_install_append() {
    ln -s ${datadir}/zoneinfo/${DEFAULT_TIMEZONE} ${D}${sysconfdir}/localtime
}
