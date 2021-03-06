#@DESCRIPTION: Machine configuration for Intel Atom based PCs with integrated
# intel graphics.

require ../../../layers/meta-yocto-bsp/conf/machine/atom-pc.conf

IMAGE_FSTYPES += "tar.bz2"

MACHINE_FEATURES += "iwlagn"

PREFERRED_PROVIDER_virtual/kernel = "linux-yocto"
PREFERRED_VERSION_linux-yocto = "3.4%"

XSERVER += "mesa-dri-driver-i965"

MACHINE_EXTRA_RRECOMMENDS = "kernel-modules eee-acpi-scripts kernel-module-snd-hwdep kernel-module-snd-rawmidi kernel-module-snd-ua101 kernel-module-snd-usb-6fire kernel-module-snd-usb-audio kernel-module-snd-usb-caiaq kernel-module-snd-usbmidi-lib kernel-module-snd-usb-us122l kernel-module-snd-usb-usx2y kernel-module-rt2500usb kernel-module-rt2800lib kernel-module-rt2800usb kernel-module-rt2x00lib kernel-module-rt2x00usb"

APPEND += "quiet"

BBMASK = ".*/meta-ti/|.*/meta-raspberrypi|.*/recipes-bsp/ti-appends/|.*/recipes-bsp/rpi-appends|.*/recipes-kernel/linux/linux-ti*|.*/meta-intel|.*/meta-nuc"
