FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-0.10.36:"

PRINC = "2"
DEPENDS += "orc orc-native"

EXTRA_OECONF += " --disable-ivorbis --enable-orc"

python populate_packages_prepend () {
    pn = d.getVar('PN', True)
    metapkg = pn + '-glib'
    d.setVar('ALLOW_EMPTY_' + metapkg, "1")
    d.setVar('FILES_' + metapkg, "")

    metapkg = pn + '-apps'
    d.setVar('ALLOW_EMPTY_' + metapkg, "1")
    d.setVar('FILES_' + metapkg, "")
}

