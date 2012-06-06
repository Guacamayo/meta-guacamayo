
RDEPENDS_${PN} += "glib-2.0-utils"
FILES_${PN} += "${datadir}/glib-2.0/schemas"

gsettings_postinstrm () {
if [ "x$D" = "x" ]; then
  glib-compile-schemas  ${datadir}/glib-2.0/schemas
fi
}

python populate_packages_append () {
	pkg = d.getVar('PN', True)
	bb.note("adding gsettings postinst scripts to %s" % pkg)

	postinst = d.getVar('pkg_postinst_%s' % pkg, True) or d.getVar('pkg_postinst', True)
	if not postinst:
		postinst = '#!/bin/sh\n'
	postinst += d.getVar('gsettings_postinstrm', True)
	d.setVar('pkg_postinst_%s' % pkg, postinst)

	bb.note("adding gsettings postrm scripts to %s" % pkg)

	postrm = d.getVar('pkg_postrm_%s' % pkg, True) or d.getVar('pkg_postrm', True)
	if not postrm:
		postrm = '#!/bin/sh\n'
	postrm += d.getVar('gsettings_postinstrm', True)
	d.setVar('pkg_postrm_%s' % pkg, postrm)
}

