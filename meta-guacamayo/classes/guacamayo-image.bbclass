
# GUACAMAYO_FEATURES controls content of guacamayo images
#
# By default we install apps-console-core and task-guacamayo-core
#
# Available features (in addition to those form core-image):
#
# - guacamayo-restricted: packages with restrictive licensing
# - guacamayo-mex: Mex media explorer
# - guacamayo-devtools: extra tools

PACKAGE_GROUP_guacamayo-core             = "task-guacamayo-core"
PACKAGE_GROUP_guacamayo-renderer         = "task-guacamayo-renderer"
PACKAGE_GROUP_guacamayo-audioplayer      = "task-guacamayo-audioplayer"
PACKAGE_GROUP_guacamayo-server           = "task-guacamayo-server"
PACKAGE_GROUP_guacamayo-mex              = "task-guacamayo-mex"
PACKAGE_GROUP_guacamayo-mex-x11          = "task-guacamayo-mex-x11"
PACKAGE_GROUP_guacamayo-mex-x11-tests    = "task-guacamayo-mex-x11-tests"
PACKAGE_GROUP_guacamayo-mex-egl          = "task-guacamayo-mex-egl"
PACKAGE_GROUP_guacamayo-mex-egl-tests    = "task-guacamayo-mex-egl-tests"
PACKAGE_GROUP_guacamayo-restricted-core  = "task-guacamayo-restricted-core"
PACKAGE_GROUP_guacamayo-restricted-audio = "task-guacamayo-restricted-audio"
PACKAGE_GROUP_guacamayo-restricted-video = "task-guacamayo-restricted-video"
PACKAGE_GROUP_guacamayo-gles-tests       = "task-guacamayo-gles-tests"
PACKAGE_GROUP_guacamayo-devtools         = "task-guacamayo-devtools"
PACKAGE_GROUP_guacamayo-demos-pictures   = "task-guacamayo-demos-pictures"
PACKAGE_GROUP_guacamayo-demos-audio      = "task-guacamayo-demos-audio"
PACKAGE_GROUP_guacamayo-demos-video      = "task-guacamayo-demos-video"
PACKAGE_GROUP_guacamayo-openmax          = "task-guacamayo-openmax"

# The core bits of Guacamayo
IMAGE_FEATURES =+ "splash ssh-server-dropbear guacamayo-core"

# Configurable Guacamayo features
IMAGE_FEATURES =+ "${GUACAMAYO_FEATURES}"

inherit core-image

# RaspberryPi config in the upstream bsp layer is now generated via a
# rpi-config package, but we need config to be different for different images
# So, run sed on the config.txt file before we do the rootfs

do_rootfs_prepend_raspberrypi() {
	echo "Tweaking RPI config"
	if [ -n "${KEY_DECODE_MPG2}" ]; then
		sed -i '/^#\?decode_MPG2=/ c\decode_MPG2=${KEY_DECODE_MPG2}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	else
		sed -i '/^#\?decode_MPG2=/ c\#decode_MPG2=\"\"' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${KEY_DECODE_WVC1}" ]; then
		sed -i '/^\?#decode_WVC1=/ c\decode_MVC1=${KEY_DECODE_WVC1}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	else
		sed -i '/^\?#decode_WVC1=/ c\#decode_MVC1=\"\"' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${DISABLE_OVERSCAN}" ]; then
		sed -i '/^#\?disable_overscan=/ c\disable_overscan=${DISABLE_OVERSCAN}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	else
		sed -i '/^#\?disable_overscan=/ c\#disable_overscan=\"\"' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${ARM_FREQ}" ]; then
		sed -i '/^#\?arm_freq=/ c\arm_freq=${ARM_FREQ}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	else
		sed -i '/^#\?arm_freq=/ c\#arm_freq=\"\"' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${CORE_FREQ}" ]; then
		sed -i '/^#\?core_freq=/ c\core_freq=${CORE_FREQ}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	else
		sed -i '/^#\?core_freq=/ c\#core_freq=\"\"' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${SDRAM_FREQ}" ]; then
		sed -i '/^#\?sdram_freq=/ c\sdram_freq=${SDRAM_FREQ}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	else
		sed -i '/^#\?sdram_freq=/ c\#sdram_freq=\"\"' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${OVER_VOLTAGE}" ]; then
		sed -i '/^#\?over_voltage=/ c\over_voltage=${OVER_VOLTAGE}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	else
		sed -i '/^#\?over_voltage=/ c\#over_voltage=\"\"' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi

	# GPU memory
	if [ -n "${GPU_MEM}" ]; then
		sed -i '/^#\?gpu_mem=/ c\gpu_mem=${GPU_MEM}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	else
		sed -i '/^#\?gpu_mem=/ c\#gpu_mem=\"\"' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${GPU_MEM_256}" ]; then
		sed -i '/^#\?gpu_mem_256=/ c\gpu_mem_256=${GPU_MEM_256}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	else
		sed -i '/^#\?gpu_mem_256=/ c\#gpu_mem_256=\"\"' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
	if [ -n "${GPU_MEM_512}" ]; then
		sed -i '/^#\?gpu_mem_512=/ c\gpu_mem_512=${GPU_MEM_512}' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	else
		sed -i '/^#\?gpu_mem_512=/ c\#gpu_mem_512=\"\"' ${DEPLOY_DIR_IMAGE}/bcm2835-bootfiles/config.txt
	fi
}
