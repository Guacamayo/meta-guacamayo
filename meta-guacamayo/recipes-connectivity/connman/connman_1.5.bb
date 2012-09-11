require connman.inc

# 1.5 tag
SRCREV = "270d7effb72bf99dfc2003ddccdb2193b1828518"
SRC_URI  = "git://git.kernel.org/pub/scm/network/connman/connman.git \
            file://add_xuser_dbus_permission.patch \
            file://connman"
S = "${WORKDIR}/git"
PR = "${INC_PR}.0"
