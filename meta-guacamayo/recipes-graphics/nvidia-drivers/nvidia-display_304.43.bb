# nvidia-display .bb build file
# Copyright (C) 2005-2006, Advanced Micro Devices, Inc.  All Rights Reserved
# Copyright (C) 2012, sleep(5) ltd
# Released under the MIT license (see /COPYING)

require nvidia-drivers.inc
require nvidia-display.inc

PR = "r0.${NVDRIVERPR}.${NVDISPLAYPR}"

SRC_URI[md5sum] = "393260b7e8d8195e982eb718f5014c3d"
SRC_URI[sha256sum] = "7c7f483302a0a5a828b8ef1f5a4a53f8e16d3b73d0c17bd61ed0d843cad32c4c"

