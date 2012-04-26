# Include this file for kernel recipes that need to handle different defconfigs
# and apply patches based on "tipspkernel" DISTRO_FEATURES

# We don't want "tipspkernel" to be another OVERRIDE, but rather a new level of
# FILESPATH with all the OVERRIDES applied to it
CONFIGS_PSP = "${@base_set_filespath(["${FILE_DIRNAME}/${PF}/tipspkernel", "${FILE_DIRNAME}/${P}/tipspkernel", \
"${FILE_DIRNAME}/${PN}/tipspkernel", "${FILE_DIRNAME}/${BP}/tipspkernel", "${FILE_DIRNAME}/${BPN}/tipspkernel", \
"${FILE_DIRNAME}/files/tipspkernel", "${FILE_DIRNAME}/tipspkernel"], d)}:"

# When "tipspkernel" is ENABLED, prepend additional set of directories to FILESPATH
FILESPATH =. "${@base_contains('DISTRO_FEATURES', 'tipspkernel', "${CONFIGS_PSP}", "", d)}"

# When "tipspkernel" is NOT ENABLED, append additional list of PATCHES_OVER_PSP to SRC_URI
SRC_URI_append = "${@base_contains('DISTRO_FEATURES', 'tipspkernel', "", "${PATCHES_OVER_PSP}", d)}"

# List additional patches to apply on top of PSP kernel, start with a space
PATCHES_OVER_PSP = ""
