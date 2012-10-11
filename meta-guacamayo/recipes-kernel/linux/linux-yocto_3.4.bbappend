FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-3.4:"

SRC_URI += "file://snd-usb.cfg \
	    file://rt2xxxUSB.cfg \
	   "

PRINC = "1"

KMACHINE_atom-egl  = "atom-pc"
KBRANCH_atom-egl  = "standard/common-pc/atom-pc"
SRCREV_machine_atom-egl ?= "59c3ff750831338d05ab67d5efd7fc101c451aff"
COMPATIBLE_MACHINE_atom-egl = "atom-egl"

#module_autoload_iwlwifi = "iwlwifi"
KERNEL_FEATURES_append_atom-pc += "iwlagn"
KERNEL_FEATURES_append_atom-egl += "iwlagn"
