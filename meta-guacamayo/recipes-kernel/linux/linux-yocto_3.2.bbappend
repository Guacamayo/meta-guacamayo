FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-3.2:"

SRC_URI += "file://snd-usb.cfg \
	    file://rt2xxxUSB.cfg \
	   "

PRINC = "2"

#module_autoload_iwlwifi = "iwlwifi"
KERNEL_FEATURES_append_atom-pc += "iwlagn"
