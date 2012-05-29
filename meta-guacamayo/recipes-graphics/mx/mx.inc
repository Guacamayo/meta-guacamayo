DESCRIPTION = "Clutter based widget library"
LICENSE = "LGPLv2.1"

INC_PR = "r3"

DEPENDS = "clutter-1.10 dbus-glib gdk-pixbuf"

inherit autotools gettext gtk-doc

EXTRA_OECONF = "--disable-introspection --disable-gtk-widgets --with-dbus --with-winsys=none --without-clutter-imcontext --without-clutter-gesture --without-startup-notification --without-glade"

