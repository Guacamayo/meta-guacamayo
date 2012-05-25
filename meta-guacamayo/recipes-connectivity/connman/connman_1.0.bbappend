
DEPENDS  = "dbus glib-2.0 ppp iptables gnutls \
            ${@base_contains('DISTRO_FEATURES', 'bluetooth','bluez4', '', d)} \
            ${@base_contains('DISTRO_FEATURES', 'wifi','wpa-supplicant', '', d)} \
            "

EXTRA_OECONF += " --disable-ofono"

PRINC = "1"
