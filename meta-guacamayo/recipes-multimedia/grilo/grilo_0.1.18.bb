SUMMARY = "Media discovery framework"
DESCRIPTION = "Grilo is a framework focused on making media discovery and browsing easy for application developers."
HOMEPAGE = "http://live.gnome.org/Grilo"

LICENSE = "LGPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

DEPENDS = "glib-2.0 libxml2 gtk+ gconf libsoup-2.4"

SRC_URI[archive.md5sum] = "c2f34727afedf6e2febad53b20218395"
SRC_URI[archive.sha256sum] = "dc5bc1ea74ed3d47c4ec2d94886234346671509bdc63ce181f98824e7178ae83"

PR = "r0"

inherit gnome

# gnomebase.bbclass assumes .bz2 rather than .xz, so we have to set this ourselves
SRC_URI = "http://ftp.gnome.org/pub/GNOME/sources/${BPN}/0.1/${BPN}-${PV}.tar.xz;name=archive"

