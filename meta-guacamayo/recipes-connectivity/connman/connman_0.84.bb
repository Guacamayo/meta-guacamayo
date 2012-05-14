require connman.inc

# 0.94 tag
SRCREV = "d3eeeb5c030333ebc0d8595a4a50fda80ab9500a"
SRC_URI  = "git://git.kernel.org/pub/scm/network/connman/connman.git \
            file://add_xuser_dbus_permission.patch \
            file://connman"
S = "${WORKDIR}/git"
PR = "r1"
