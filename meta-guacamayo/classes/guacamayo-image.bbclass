
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
PACKAGE_GROUP_guacamayo-headless         = "task-guacamayo-headless"
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

# The core bits of Guacamayo
IMAGE_FEATURES =+ "apps-console-core ssh-server-dropbear guacamayo-core"

# Configurable Guacamayo features
IMAGE_FEATURES =+ "${GUACAMAYO_FEATURES}"

inherit core-image
