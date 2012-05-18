# Vala has problems with multiple concurrent invocations
PARALLEL_MAKE = ""

# Vala needs vala-native
DEPENDS += "vala-native"
DEPENDS_virtclass-native += "vala-native"

# Our patched version of Vala looks in STAGING_DATADIR for .vapi files
export STAGING_DATADIR
# Upstream Vala >= 0.11 looks in XDG_DATA_DIRS for .vapi files
export XDG_DATA_DIRS = "${STAGING_DATADIR}"

# Package additional files
FILES_${PN}-dev += "\
  ${datadir}/vala/vapi/*.vapi \
  ${datadir}/vala/vapi/*.deps \
  ${datadir}/gir-1.0 \
"
