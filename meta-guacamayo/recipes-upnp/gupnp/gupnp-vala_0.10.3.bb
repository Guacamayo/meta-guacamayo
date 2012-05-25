SUMMARY = "Vala bindings for gupnpn"
LICENSE = "LGPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=3bf50002aefd002f49e7bb854063f7e7"
DEPENDS = "gupnp gupnp-av gupnp-dlna gssdp"

include gupnp-vala.inc

PR = "${INC_PR}.0"

SRC_URI[archive.md5sum] = "7ffe2fff14ae8adf3111e09008862b5c"
SRC_URI[archive.sha256sum] = "6d463c1c19786453dd06787e0829afa1e6f51421c91b769bac09be97cc54e9d4"

