# nvidia-display .bb build file
# Copyright (C) 2005-2006, Advanced Micro Devices, Inc.  All Rights Reserved
# Copyright (C) 2012, sleep(5) ltd
# Released under the MIT license (see /COPYING)

NVDRIVERPR = "0"

HOMEPAGE="http://www.nvidia.com/object/unix.html"
DESCRIPTION="NVidia Graphics Driver Binary"
LICENSE = "NVIDIA"

inherit module
INHIBIT_PACKAGE_STRIP_${PN} = "1"

def nvidia_arch(d):
	import bb, re
	arch = bb.data.getVar('TARGET_ARCH', d, 1)
	if re.match("^i.86$", arch):
		arch = "x86"
	return arch

def get_kernelpatchlevel(p):
	import re
	r = re.compile("[0-9]+\.([0-9]+).*")
	m = r.match(p);
	if m:
		return m.group(1)
	return None

NVIDIA_ARCH := "${@nvidia_arch(d)}"

COMPATIBLE_HOST = '.*86.*-linux'

KERNEL_PATCHLEVEL = "${@get_kernelpatchlevel('${KERNEL_VERSION}')}"
EXTRA_OEMAKE=" SYSSRC=${STAGING_KERNEL_DIR} KERNEL_SOURCES=${STAGING_KERNEL_DIR} KERNEL_MODLIB=${STAGING_KERNEL_DIR} KERNEL_UNAME=${KERNEL_VERSION} PATCHLEVEL=${KERNEL_PATCHLEVEL} MODULE_ROOT=${D}/lib/modules/${KERNEL_VERSION}/kernel/drivers IGNORE_CC_MISMATCH=1"

PKG_BASENAME="NVIDIA-Linux-${NVIDIA_ARCH}-${PV}"

FILES_${PN}-doc += " /usr/share/doc/*"

nvidia_do_unpack() {
	cd ${WORKDIR}
	# the Nvidia installer fails if this directory already exist
	rm -rf ${PKG_BASENAME}
	sh ./${PKG_BASENAME}.run -x

	# copy the license from the unpack dir to S (the license check
        # is too daft to find it otherwise)
	cp ${WORKDIR}/${PKG_BASENAME}/LICENSE ${S}
}

python do_unpack() {
	bb.build.exec_func('base_do_unpack', d)
	bb.build.exec_func('nvidia_do_unpack', d)
}

