require libgee.inc
PE = "1"
PR = "${INC_PR}.1"
#autoreconf needs introspection.m4 (staged by gobject-introspection-native) after http://git.gnome.org/browse/libgee/commit/?id=d026a29b38ca1a3388981c6e75a92602212373d8
DEPENDS += "gobject-introspection-native"
DEPENDS_virtclass-native += "gobject-introspection-native"

LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

SRC_URI[archive.md5sum] = "a32bf498cf33d5e3417823a7b252ad22"
SRC_URI[archive.sha256sum] = "55f39f3b28e676f6cbd9377d83edd031084436a4da41280a9503c94faffb1665"
