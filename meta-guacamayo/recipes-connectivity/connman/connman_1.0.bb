require connman.inc

# 0.94 tag
SRCREV = "6d6f312fb2b751b4cf7037f2a526c7785364732f"
SRC_URI  = "git://git.kernel.org/pub/scm/network/connman/connman.git \
            file://add_xuser_dbus_permission.patch \
            file://connman"
S = "${WORKDIR}/git"
PR = "r3"
