SUMMARY = "Demo Meta Package"
DESCRIPTION = "Demo Meta Package"
LICENSE = "MIT"

PR = "r2"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit allarch

ALLOW_EMPTY = "1"

RDEPENDS = "guacamayo-demos-pictures	\
	    guacamayo-demos-audio	\
	    guacamayo-demos-video	\
	   "
