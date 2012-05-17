DESCRIPTION = "gvfs is a userspace virtual filesystem"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7"

inherit gnome

INC_PR = "r7"

SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/gvfs/1.12/${BPN}-${PV}.tar.xz;name=archive"

SRC_URI[archive.md5sum] = "348edf09eca150ba7065017127f63c64"
SRC_URI[archive.sha256sum] = "38f69ec92083f86a51e7814e98d2861151f16dfb3abe4b3b39488408e12fd9c2"

EXTRA_OECONF = "--disable-samba \
                --with-samba-includes=${STAGING_INCDIR} \
                --with-samba-libs=${STAGING_LIBDIR} \
                --with-archive-includes=${STAGING_INCDIR} \
               "

FILES_${PN} += "${datadir}/dbus-1/services/*"

