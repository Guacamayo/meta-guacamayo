DESCRIPTION = "Guacamayo overrides for gsettings"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r2"

# This should really be an allarch package, but image build will fail because
# of glib-2.0-utils RDEPENDS

inherit gsettings

SRC_URI = "file://guacamayo.gschema.override"

DEPENDS = "dconf"

do_install() {

    install -d ${D}/${datadir}/glib-2.0/schemas
    install ${WORKDIR}/*.gschema.override ${D}/${datadir}/glib-2.0/schemas/
}
