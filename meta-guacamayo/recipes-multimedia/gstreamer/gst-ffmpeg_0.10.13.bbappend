
PRINC = "1"

# The new configure line that explicitely specifies the --cc and --as options
# Flushes out a bug in the ffmpeg copy of libav (the same bug impacts the
# standalone libav, see the libav.bbappend in meta-raspberrypi, which has a
# patch and some configure options
# For now, revert back to the configure line that was used in denzil
EXTRA_OECONF_raspberrypi = "--with-ffmpeg-extra-configure=\"--target-os=linux\" ${GSTREAMER_DEBUG}"
