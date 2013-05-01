FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://etc"

PRINC = "1"

do_install_append () {
        # remove stray back up files
	find ${WORKDIR}/etc -name "*~" -type f -print0 | xargs -0 /bin/rm -f

	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/etc/udev/rules.d/*.rules \
			${D}${sysconfdir}/udev/rules.d/
}
