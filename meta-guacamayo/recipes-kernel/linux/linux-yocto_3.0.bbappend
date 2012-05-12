FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-3.0:"

SRC_URI += "file://snd-usb.cfg"

PRINC = "1"

#module_autoload_iwlwifi = "iwlwifi"
KERNEL_FEATURES_append_atom-pc += "iwlagn"
