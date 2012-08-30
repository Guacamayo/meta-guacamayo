

PRINC = "1"

# The TI CG Tools cannot be downloaded annonymously, as it subject to US
# export restrictions, so you have to fetch it manually from
# https://www-a.ti.com/downloads/sds_support/TICodegenerationTools/download.htm
# once you download the file, set the MYFILELOCATION variable below here to the
# directory where your downloaded file is.

MYFILELOCATION="/ext/poky/downloads"

SRC_URI = "file://${MYFILELOCATION}/ti_cgt_c6000_${PVwithdots}_setup_linux_x86.bin;name=cgt6xbin"

do_postfetch () {
  cp ${MYFILELOCATION}/ti_cgt_c6000_${PVwithdots}_setup_linux_x86.bin ${WORKDIR}
}

addtask do_postfetch after do_fetch before do_unpack
