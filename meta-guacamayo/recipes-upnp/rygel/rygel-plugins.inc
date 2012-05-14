LIBV = "1.0"

python populate_packages_prepend () {
	rygel_libdir = d.expand('${libdir}/rygel-${LIBV}')
	postinst = d.getVar('plugin_postinst', True)

	do_split_packages(d, rygel_libdir, 'librygel(.*)\.so$', d.expand('${PN}-plugin%s'), 'Rygel plugin for %s', postinst=postinst, extra_depends=d.expand('${PN}'))
	do_split_packages(d, rygel_libdir, 'librygel(.*)\.la$', d.expand('${PN}-plugin%s-dev'), 'Rygel plugin for %s (development files)', extra_depends=d.expand('${PN}-dev'))
	do_split_packages(d, rygel_libdir, 'librygel(.*)\.a$', d.expand('${PN}-plugin%s-staticdev'), 'Rygel plugin for %s (static development files)', extra_depends=d.expand('${PN}-staticdev'))

	pn = d.getVar('PN', True)
	metapkg =  pn + '-meta'
	d.setVar('ALLOW_EMPTY_' + metapkg, "1")
	d.setVar('FILES_' + metapkg, "")
	blacklist = [ pn + '-locale', pn + '-dev', pn + '-dbg', pn + '-doc' ]
	metapkg_rdepends = []
	packages = d.getVar('PACKAGES', True).split()
	for pkg in packages[1:]:
		if not pkg in blacklist and not pkg in metapkg_rdepends and not pkg.endswith('-dev') and not pkg.endswith('-dbg') and not pkg.count('locale') and not pkg.count('-staticdev'):
			metapkg_rdepends.append(pkg)
	d.setVar('RDEPENDS_' + metapkg, ' '.join(metapkg_rdepends))
	d.setVar('DESCRIPTION_' + metapkg, pn + ' meta package')
}

ALLOW_EMPTY = "1"

PACKAGES += "${PN}-meta"
