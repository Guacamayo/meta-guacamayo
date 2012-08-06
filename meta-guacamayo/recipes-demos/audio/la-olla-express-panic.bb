
SUMMARY = "La Olla Express, Panic"
DESCRIPTION = "Panic by La Olla Express"
HOMEPAGE = "http://http://www.myspace.com/laolla"
LICENSE = "CC-BY-NC-SA-3.0"

PR = "r1"

LIC_FILES_CHKSUM = "file://LICENSE;md5=885175b8d808263b0e483733919876fe"

FILESEXTRAPATHS_prepend := "${THISDIR}/la-olla-express-panic:"

# This is all bit hackish because the Poky fetcher is letting us down rather
# badly here: the file we download will be named after the last component of
# the uri, which in this case is constant 'mp32', so each file would overrided
# the previous one, though in practice as soon as the second file is downloaded
# the checksum check will fail on the first one. So we simply download the
# track in do_configure using wget fixing up the storate name.
#
# Recent poky supports a 'downloadfilename' parameter, which allows to do this
# properly, the commented out code below implements this for future reference
# (minus the checksums)
#
#SRC_URI[track1.md5sum] = "68a4828b54820dd795d40f0859ba6ffe"
#SRC_URI[track1.sha256sum] = "cb7be60feb4fd4ff5b006e35e824201a48361b7ebc2f2bbcaf3351912035561a"
# etc.
#
#SRC_URI = "\
#   http://storage-new.newjamendo.com/download/track/415050/mp32;name=track1;downloadfilename=track1.mp3 \
#   http://storage-new.newjamendo.com/download/track/415083/mp32;name=track2;downloadfilename=track2.mp3 \
#   http://storage-new.newjamendo.com/download/track/415093/mp32;name=track3;downloadfilename=track3.mp3 \
#   http://storage-new.newjamendo.com/download/track/415103/mp32;name=track4;downloadfilename=track4.mp3 \
#   http://storage-new.newjamendo.com/download/track/415106/mp32;name=track5;downloadfilename=track5.mp3 \
#   http://storage-new.newjamendo.com/download/track/415142/mp32;name=track6;downloadfilename=track6.mp3 \
#   http://storage-new.newjamendo.com/download/track/415160/mp32;name=track7;downloadfilename=track7.mp3 \
#   http://storage-new.newjamendo.com/download/track/415168/mp32;name=track8;downloadfilename=track8.mp3 \
#   http://storage-new.newjamendo.com/download/track/415169/mp32;name=track9;downloadfilename=track9.mp3 \
#   http://storage-new.newjamendo.com/download/track/415186/mp32;name=track10;downloadfilename=track10.mp3 \
#   http://storage-new.newjamendo.com/download/track/415199/mp32;name=track11;downloadfilename=track11.mp3 \
#   http://storage-new.newjamendo.com/download/track/415207/mp32;name=track12;downloadfilename=track12.mp3 \
#   http://storage-new.newjamendo.com/download/track/415208/mp32;name=track13;downloadfilename=track13.mp3 \
#   file://LICENSE"

SRC_URI = "file://LICENSE"

inherit allarch

do_configure_prepend () {
   cp ${WORKDIR}/LICENSE ${S}

   wget -r -O track1.mp3 http://storage-new.newjamendo.com/download/track/415050/mp32
   wget -r -O track2.mp3 http://storage-new.newjamendo.com/download/track/415083/mp32
   wget -r -O track3.mp3 http://storage-new.newjamendo.com/download/track/415093/mp32
   wget -r -O track4.mp3 http://storage-new.newjamendo.com/download/track/415103/mp32
   wget -r -O track5.mp3 http://storage-new.newjamendo.com/download/track/415106/mp32
   wget -r -O track6.mp3 http://storage-new.newjamendo.com/download/track/415142/mp32
   wget -r -O track7.mp3 http://storage-new.newjamendo.com/download/track/415160/mp32
   wget -r -O track8.mp3 http://storage-new.newjamendo.com/download/track/415168/mp32
   wget -r -O track9.mp3 http://storage-new.newjamendo.com/download/track/415169/mp32
   wget -r -O track10.mp3 http://storage-new.newjamendo.com/download/track/415186/mp32
   wget -r -O track11.mp3 http://storage-new.newjamendo.com/download/track/415199/mp32
   wget -r -O track12.mp3 http://storage-new.newjamendo.com/download/track/415207/mp32
   wget -r -O track13.mp3 http://storage-new.newjamendo.com/download/track/415208/mp32
}

do_install() {
    install -d ${D}${datadir}/demos/audio/La_Olla_Express/Panic
    install -m 0644 ${S}/*.mp3 ${D}${datadir}/demos/audio/La_Olla_Express/Panic
    install -m 0644 ${WORKDIR}/LICENSE ${D}${datadir}/demos/audio/La_Olla_Express/Panic/LICENSE
}

FILES_${PN} += "${datadir}"
