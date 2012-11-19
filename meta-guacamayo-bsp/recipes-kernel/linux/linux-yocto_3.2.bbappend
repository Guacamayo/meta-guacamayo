FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-3.2:"

SRC_URI += "file://snd-usb.cfg \
	    file://rt2xxxUSB.cfg \
	   "

PRINC = "3"

KMACHINE_atom-egl  = "atom-pc"
KBRANCH_atom-egl  = "standard/default/common-pc/atom-pc"
SRCREV_machine_atom-egl ?= "f29531a41df15d74be5ad47d958e4117ca9e489e"
COMPATIBLE_MACHINE_atom-egl = "atom-egl"

#module_autoload_iwlwifi = "iwlwifi"
KERNEL_FEATURES_append_atom-pc += "iwlagn"
KERNEL_FEATURES_append_atom-egl += "iwlagn"
