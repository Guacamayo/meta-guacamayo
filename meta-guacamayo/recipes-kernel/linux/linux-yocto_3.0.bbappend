FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-3.0:"

SRC_URI += "file://snd-usb.cfg \
	    file://rt2xxxUSB.cfg \
	   "

PRINC = "3"

#module_autoload_iwlwifi = "iwlwifi"
KERNEL_FEATURES_append_atom-pc += "iwlagn"

KMACHINE_atom-nvidia  = "yocto/standard/common-pc/atom-pc"
SRCREV_machine_atom-nvidia ?= "afdda882f902dd28693cd8701a7d497958290f09"
COMPATIBLE_MACHINE_atom-nvidia = "atom-nvidia"
KERNEL_FEATURES_append_atom-nvidia += "iwlagn"
