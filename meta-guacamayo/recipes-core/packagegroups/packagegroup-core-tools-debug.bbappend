
PRINC = "1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN} = "\
    gdb \
    gdbserver \
    tcf-agent \
    rsync \
    strace \
    ${MTRACE} \
    "
