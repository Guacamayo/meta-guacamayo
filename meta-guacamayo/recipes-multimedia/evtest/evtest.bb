DESCRIPTION = "Simple tool for testing evdev input events"
SECTION = "x11/multimedia"
LICENSE = "GPLv2"

inherit autotools

LIC_FILES_CHKSUM = "file://evtest.c;beginline=1;endline=27;md5=123cc8cb450149fc894b19d070a74410"

SRC_URI = "file://evtest.c"

S = "${WORKDIR}"

PR = "r0"

do_configure_prepend (){
     echo "AC_PREREQ(2.53)" > configure.ac
     echo "AC_INIT([evtest], [evtest], [evtest])" >> configure.ac
     echo "AC_CONFIG_SRCDIR([evtest.c])" >> configure.ac
     echo "AM_INIT_AUTOMAKE()" >> configure.ac
     echo "AC_PROG_CC" >> configure.ac
     echo "AC_OUTPUT([Makefile])" >> configure.ac

     echo "bin_PROGRAMS = evtest" > Makefile.am
     echo "evtest_SOURCES = evtest.c" >> Makefile.am
}
