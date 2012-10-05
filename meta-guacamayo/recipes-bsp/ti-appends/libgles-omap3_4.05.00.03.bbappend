FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://etc"

PRINC = "9"

PROVIDES += "virtual/libgles2"
RPROVIDES += "libgles libgles2"

# remove the powervr.ini file from the main package, and package several
# different configs separately
CONFFILES_${PN} = ""

PACKAGES =+ "${PN}-pvrini-native ${PN}-pvrini-dri ${PN}-pvrini-x11"

# the TI unpack task does no checking and will silently fail, so check here
# (One reason for such a failure is lack of 32-bit libs on 64-bit host, which
# are needed by the sdk binary.)
python do_unpackextra () {
	bb.note('Checking whether binary installer was unpacked successfully')
	workdir = d.getVar('WORKDIR', True)
	tspa = os.path.join(workdir, 'TSPA.txt')

	if not os.path.isfile(tspa):
		raise bb.build.FuncFailed("Unpack failed: no %s: do you have 32-bit compatibility libraries installed?" % tspa)
}

addtask do_unpackextra after do_unpack before do_patch

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
    bb.utils.copyfile(src,dst)
    return 0

python populate_packages_append () {
	pn = d.expand('${PN}')
	fixup_ini(pn + '-pvrini-native', 'powervr.ini.native', d)
	fixup_ini(pn + '-pvrini-dri', 'powervr.ini.dri', d)
	fixup_ini(pn + '-pvrini-x11', 'powervr.ini.x11', d)
}

FILES_${PN}-pvrini-native = "${sysconfdir}/powervr.ini.native"
FILES_${PN}-pvrini-dri = "${sysconfdir}/powervr.ini.dri"
FILES_${PN}-pvrini-x11 = "${sysconfdir}/powervr.ini.x11"

RDEPENDS_${PN}-pvrini-native += "libgles-omap3-flipwsegl"
RDEPENDS_${PN}-pvrini-dri += "libgles-omap3-driwsegl"
RDEPENDS_${PN}-pvrini-x11 += "libgles-omap3-x11wsegl"

CONFFILES_${PN}-pvrini-native = "${sysconfdir}/powervr.ini"
CONFFILES_${PN}-pvrini-dri = "${sysconfdir}/powervr.ini"
CONFFILES_${PN}-pvrini-x11 = "${sysconfdir}/powervr.ini"

FILES_${PN}-staticdev += "${libdir}/ES*/*.a"
