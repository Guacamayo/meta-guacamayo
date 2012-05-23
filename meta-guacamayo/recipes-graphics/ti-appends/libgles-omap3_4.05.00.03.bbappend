FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://etc"

PRINC = "2"

# remove the powervr.ini file from the main package, and package several
# different configs separately
CONFFILES_${PN} = ""

PACKAGES =+ "${PN}-pvrini-native ${PN}-pvrini-dri"

do_install_append() {
    rm ${D}${sysconfdir}/powervr.ini
    cp -R ${WORKDIR}/etc/* ${D}${sysconfdir}/
}

def fixup_ini(name, oldfile, d):
    import bb
    pn = d.expand('${PN}')
    workdir = d.expand('${WORKDIR}')
    sysconf = d.expand('${sysconfdir}')
    thesplit = os.path.join(workdir, 'packages-split')
    thepkg = os.path.join(thesplit, name)
    thepkgsys = os.path.join(thepkg, os.path.basename(sysconf))
    src = os.path.join(thepkgsys, oldfile)
    dst = os.path.join(thepkgsys, 'powervr.ini')
    bb.note("moving %s -> %s" % (src,dst))
    os.rename(src,dst);
    return 0

python populate_packages_append () {
	pn = d.expand('${PN}')
	fixup_ini(pn + '-pvrini-native', 'powervr.ini.native', d)
	fixup_ini(pn + '-pvrini-dri', 'powervr.ini.dri', d)
}

FILES_${PN}-pvrini-native = "${sysconfdir}/powervr.ini.native"
FILES_${PN}-pvrini-dri = "${sysconfdir}/powervr.ini.dri"

RDEPENDS_${PN}-pvrini-native += "libgles-omap3-flipwsegl"
RDEPENDS_${PN}-pvrini-dri += "libgles-omap3-driwsegl"

CONFFILES_${PN}-pvrini-native = "${sysconfdir}/powervr.ini"
CONFFILES_${PN}-pvrini-dri = "${sysconfdir}/powervr.ini"
