FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PRINC = "4"

# since we are patching that file, we have to change the checksum
# (the patch has been submitted upstream, so it will eventually go)
LIC_FILES_CHKSUM = "file://psplash.h;md5=6a85171ceb2cad75966d2aba2a4669df"

SRC_URI += "file://0001-Make-it-easier-to-customise-colours.patch \
	    file://psplash-colors.h \
	    file://psplash-bar-img.png"

# NB: this is only for the main logo image; if you add multiple images here,
#     poky will build multiple psplash packages with 'outsuffix' in name for
#     each of these ...
SPLASH_IMAGES = "file://psplash-poky-img.png;outsuffix=default"

# The core psplash recipe is only designed to deal with modifications to the
# 'logo' image; we need to change the bar image too, since we are changing
# colors
do_patchextra() {
	cd ${S}
	cp ../psplash-colors.h ./
	# strip the -img suffix from the bar png -- we could just store the
	# file under that suffix-less name, but that would make it confusing
        # for anyone updating the assets
	cp ../psplash-bar-img.png ./psplash-bar.png
	./make-image-header.sh ./psplash-bar.png BAR
}

addtask do_patchextra after do_patch before do_configure
