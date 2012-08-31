FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PRINC = "1"

SRC_URI += "file://uEnv.txt"

do_deploy_append () {
	cp ${WORKDIR}/uEnv.txt ${DEPLOY_DIR_IMAGE}/uEnv.txt
}
