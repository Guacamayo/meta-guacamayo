require linux.inc

DESCRIPTION = "Linux kernel for TI processors"
KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(beagleboard)"

PV = "3.0.28"
# v3.0.28 tag
SRCREV_pn-${PN} = "0527fde0639955203ad48a9fd83bd6fc35e82e07"

# The main PR is now using MACHINE_KERNEL_PR, for omap3 see conf/machine/include/omap3.inc
MACHINE_KERNEL_PR_append = "a"

FILESPATH =. "${FILE_DIRNAME}/linux-3.0:${FILE_DIRNAME}/linux-3.0/${MACHINE}:"

SRC_URI += "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git;branch=linux-3.0.y;protocol=git \
            file://pm-wip/voltdm/0001-cleanup-regulator-supply-definitions-in-mach-omap2.patch \
            file://pm-wip/voltdm/0002-Remove-old-style-supply.dev-assignments-common-in-hs.patch \
            file://pm-wip/voltdm/0003-omap-Use-separate-init_irq-functions-to-avoid-cpu_is.patch \
            file://pm-wip/voltdm/0004-omap-Set-separate-timer-init-functions-to-avoid-cpu_.patch \
            file://pm-wip/voltdm/0005-omap-Move-dmtimer-defines-to-dmtimer.h.patch \
            file://pm-wip/voltdm/0006-omap-Make-a-subset-of-dmtimer-functions-into-inline-.patch \
            file://pm-wip/voltdm/0007-omap2-Use-dmtimer-macros-for-clockevent.patch \
            file://pm-wip/voltdm/0008-omap2-Remove-gptimer_wakeup-for-now.patch \
            file://pm-wip/voltdm/0009-OMAP3-SR-make-notify-independent-of-class.patch \
            file://pm-wip/voltdm/0010-OMAP3-SR-disable-interrupt-by-default.patch \
            file://pm-wip/voltdm/0011-OMAP3-SR-enable-disable-SR-only-on-need.patch \
            file://pm-wip/voltdm/0012-OMAP3-SR-fix-cosmetic-indentation.patch \
            file://pm-wip/voltdm/0013-omap2-Reserve-clocksource-and-timesource-and-initial.patch \
            file://pm-wip/voltdm/0014-omap2-Use-dmtimer-macros-for-clocksource.patch \
            file://pm-wip/voltdm/0015-omap2-Remove-omap2_gp_clockevent_set_gptimer.patch \
            file://pm-wip/voltdm/0016-omap2-Rename-timer-gp.c-into-timer.c-to-combine-time.patch \
            file://pm-wip/voltdm/0017-omap-cleanup-NAND-platform-data.patch \
            file://pm-wip/voltdm/0018-omap-board-omap3evm-Fix-compilation-error.patch \
            file://pm-wip/voltdm/0019-omap-mcbsp-Drop-SPI-mode-support.patch \
            file://pm-wip/voltdm/0020-omap-mcbsp-Drop-in-driver-transfer-support.patch \
            file://pm-wip/voltdm/0021-omap2-fix-build-regression.patch \
            file://pm-wip/voltdm/0022-OMAP-New-twl-common-for-common-TWL-configuration.patch \
            file://pm-wip/voltdm/0023-OMAP4-Move-common-twl6030-configuration-to-twl-commo.patch \
            file://pm-wip/voltdm/0024-OMAP3-Move-common-twl-configuration-to-twl-common.patch \
            file://pm-wip/voltdm/0025-OMAP3-Move-common-regulator-configuration-to-twl-com.patch \
            file://pm-wip/voltdm/0026-omap-mcbsp-Remove-rx_-tx_word_length-variables.patch \
            file://pm-wip/voltdm/0027-omap-mcbsp-Remove-port-number-enums.patch \
            file://pm-wip/voltdm/0028-OMAP-dmtimer-add-missing-include.patch \
            file://pm-wip/voltdm/0029-OMAP2-hwmod-Fix-smart-standby-wakeup-support.patch \
            file://pm-wip/voltdm/0030-OMAP4-hwmod-data-Add-MSTANDBY_SMART_WKUP-flag.patch \
            file://pm-wip/voltdm/0031-OMAP2-hwmod-Enable-module-in-shutdown-to-access-sysc.patch \
            file://pm-wip/voltdm/0032-OMAP2-hwmod-Do-not-write-the-enawakeup-bit-if-SYSC_H.patch \
            file://pm-wip/voltdm/0033-OMAP2-hwmod-Remove-_populate_mpu_rt_base-warning.patch \
            file://pm-wip/voltdm/0034-OMAP2-hwmod-Fix-the-HW-reset-management.patch \
            file://pm-wip/voltdm/0035-OMAP-hwmod-Add-warnings-if-enable-failed.patch \
            file://pm-wip/voltdm/0036-OMAP-hwmod-Move-pr_debug-to-improve-the-readability.patch \
            file://pm-wip/voltdm/0037-omap_hwmod-use-a-null-structure-record-to-terminate-.patch \
            file://pm-wip/voltdm/0038-omap_hwmod-share-identical-omap_hwmod_addr_space-arr.patch \
            file://pm-wip/voltdm/0039-omap_hwmod-use-a-terminator-record-with-omap_hwmod_m.patch \
            file://pm-wip/voltdm/0040-omap_hwmod-share-identical-omap_hwmod_mpu_irqs-array.patch \
            file://pm-wip/voltdm/0041-omap_hwmod-use-a-terminator-record-with-omap_hwmod_d.patch \
            file://pm-wip/voltdm/0042-omap_hwmod-share-identical-omap_hwmod_dma_info-array.patch \
            file://pm-wip/voltdm/0043-omap_hwmod-share-identical-omap_hwmod_class-omap_hwm.patch \
            file://pm-wip/voltdm/0044-OMAP4-hwmod-data-Fix-L3-interconnect-data-order-and-.patch \
            file://pm-wip/voltdm/0045-OMAP4-hwmod-data-Remove-un-needed-parens.patch \
            file://pm-wip/voltdm/0046-OMAP4-hwmod-data-Fix-bad-alignement.patch \
            file://pm-wip/voltdm/0047-OMAP4-hwmod-data-Align-interconnect-format-with-regu.patch \
            file://pm-wip/voltdm/0048-OMAP4-clock-data-Add-sddiv-to-USB-DPLL.patch \
            file://pm-wip/voltdm/0049-OMAP4-clock-data-Remove-usb_host_fs-clkdev-with-NULL.patch \
            file://pm-wip/voltdm/0050-OMAP4-clock-data-Re-order-some-clock-nodes-and-struc.patch \
            file://pm-wip/voltdm/0051-OMAP4-clock-data-Fix-max-mult-and-div-for-USB-DPLL.patch \
            file://pm-wip/voltdm/0052-OMAP4-prcm-Fix-errors-in-few-defines-name.patch \
            file://pm-wip/voltdm/0053-OMAP4-prm-Remove-wrong-clockdomain-offsets.patch \
            file://pm-wip/voltdm/0054-OMAP4-powerdomain-data-Fix-indentation.patch \
            file://pm-wip/voltdm/0055-OMAP4-cm-Remove-RESTORE-macros-to-avoid-access-from-.patch \
            file://pm-wip/voltdm/0056-OMAP4-prcm_mpu-Fix-indent-in-few-macros.patch \
            file://pm-wip/voltdm/0057-OMAP4-clockdomain-data-Fix-data-order-and-wrong-name.patch \
            file://pm-wip/voltdm/0058-OMAP-omap_device-replace-_find_by_pdev-with-to_omap_.patch \
            file://pm-wip/voltdm/0059-OMAP-PM-remove-OMAP_PM_NONE-config-option.patch \
            file://pm-wip/voltdm/0060-OMAP4-clock-data-Remove-McASP2-McASP3-and-MMC6-clock.patch \
            file://pm-wip/voltdm/0061-OMAP4-clock-data-Remove-UNIPRO-clock-nodes.patch \
            file://pm-wip/voltdm/0062-OMAP4-hwmod-data-Modify-DSS-opt-clocks.patch \
            file://pm-wip/voltdm/0063-OMAP2-PM-Initialise-sleep_switch-to-a-non-valid-valu.patch \
            file://pm-wip/voltdm/0064-OMAP4-powerdomain-data-Fix-core-mem-states-and-missi.patch \
            file://pm-wip/voltdm/0065-OMAP4-clock-data-Keep-GPMC-clocks-always-enabled-and.patch \
            file://pm-wip/voltdm/0066-OMAP4-powerdomain-data-Remove-unsupported-MPU-powerd.patch \
            file://pm-wip/voltdm/0067-OMAP4-hwmod-data-Change-DSS-main_clk-scheme.patch \
            file://pm-wip/voltdm/0068-I2C-OMAP2-Set-hwmod-flags-to-only-allow-16-bit-acces.patch \
            file://pm-wip/voltdm/0069-I2C-OMAP2-increase-omap_i2c_dev_attr-flags-from-u8-t.patch \
            file://pm-wip/voltdm/0070-I2C-OMAP2-Introduce-I2C-IP-versioning-constants.patch \
            file://pm-wip/voltdm/0071-I2C-OMAP1-OMAP2-create-omap-I2C-functionality-flags-.patch \
            file://pm-wip/voltdm/0072-I2C-OMAP2-Tag-all-OMAP2-hwmod-defintions-with-I2C-IP.patch \
            file://pm-wip/voltdm/0073-I2C-OMAP2-add-correct-functionality-flags-to-all-oma.patch \
            file://pm-wip/voltdm/0074-OMAP-hwmod-fix-the-i2c-reset-timeout-during-bootup.patch \
            file://pm-wip/voltdm/0075-OMAP-omap_device-Create-clkdev-entry-for-hwmod-main_.patch \
            file://pm-wip/voltdm/0076-OMAP4-clock-data-Add-missing-divider-selection-for-a.patch \
            file://pm-wip/voltdm/0077-OMAP4-hwmod-data-Add-clock-domain-attribute.patch \
            file://pm-wip/voltdm/0078-OMAP2-hwmod-Init-clkdm-field-at-boot-time.patch \
            file://pm-wip/voltdm/0079-OMAP4-hwmod-Replace-CLKCTRL-absolute-address-with-of.patch \
            file://pm-wip/voltdm/0080-OMAP-hwmod-Wait-the-idle-status-to-be-disabled.patch \
            file://pm-wip/voltdm/0081-OMAP4-hwmod-Replace-RSTCTRL-absolute-address-with-of.patch \
            file://pm-wip/voltdm/0082-OMAP4-prm-Replace-warm-reset-API-with-the-offset-bas.patch \
            file://pm-wip/voltdm/0083-OMAP4-prm-Remove-deprecated-functions.patch \
            file://pm-wip/voltdm/0084-OMAP4-hwmod-data-Add-PRM-context-register-offset.patch \
            file://pm-wip/voltdm/0085-OMAP4-hwmod-data-Add-modulemode-entry-in-omap_hwmod-.patch \
            file://pm-wip/voltdm/0086-OMAP4-cm-Add-two-new-APIs-for-modulemode-control.patch \
            file://pm-wip/voltdm/0087-OMAP4-hwmod-Introduce-the-module-control-in-hwmod-co.patch \
            file://pm-wip/voltdm/0088-OMAP-clockdomain-Remove-redundant-call-to-pwrdm_wait.patch \
            file://pm-wip/voltdm/0089-OMAP2-clockdomain-Add-2-APIs-to-control-clockdomain-.patch \
            file://pm-wip/voltdm/0090-OMAP2-clockdomain-add-clkdm_in_hwsup.patch \
            file://pm-wip/voltdm/0091-OMAP2-PM-idle-clkdms-only-if-already-in-idle.patch \
            file://pm-wip/voltdm/0092-OMAP2-clockdomain-Add-per-clkdm-lock-to-prevent-conc.patch \
            file://pm-wip/voltdm/0093-OMAP2-clock-allow-per-SoC-clock-init-code-to-prevent.patch \
            file://pm-wip/voltdm/0094-OMAP2-hwmod-Follow-the-recommended-PRCM-module-enabl.patch \
            file://pm-wip/voltdm/0095-OMAP-Add-debugfs-node-to-show-the-summary-of-all-clo.patch \
            file://pm-wip/voltdm/0096-OMAP2-hwmod-remove-unused-voltagedomain-pointer.patch \
            file://pm-wip/voltdm/0097-OMAP2-voltage-move-PRCM-mod-offets-into-VC-VP-struct.patch \
            file://pm-wip/voltdm/0098-OMAP2-voltage-move-prm_irqst_reg-from-VP-into-voltag.patch \
            file://pm-wip/voltdm/0099-OMAP2-voltage-start-towards-a-new-voltagedomain-laye.patch \
            file://pm-wip/voltdm/0100-OMAP3-voltage-rename-mpu-voltagedomain-to-mpu_iva.patch \
            file://pm-wip/voltdm/0101-OMAP3-voltagedomain-data-add-wakeup-domain.patch \
            file://pm-wip/voltdm/0102-OMAP3-voltage-add-scalable-flag-to-voltagedomain.patch \
            file://pm-wip/voltdm/0103-OMAP2-powerdomain-add-voltagedomain-to-struct-powerd.patch \
            file://pm-wip/voltdm/0104-OMAP2-add-voltage-domains-and-connect-to-powerdomain.patch \
            file://pm-wip/voltdm/0105-OMAP3-powerdomain-data-add-voltage-domains.patch \
            file://pm-wip/voltdm/0106-OMAP4-powerdomain-data-add-voltage-domains.patch \
            file://pm-wip/voltdm/0107-OMAP2-powerdomain-add-voltage-domain-lookup-during-r.patch \
            file://pm-wip/voltdm/0108-OMAP2-voltage-keep-track-of-powerdomains-in-each-vol.patch \
            file://pm-wip/voltdm/0109-OMAP2-voltage-split-voltage-controller-VC-code-into-.patch \
            file://pm-wip/voltdm/0110-OMAP2-voltage-move-VC-into-struct-voltagedomain-misc.patch \
            file://pm-wip/voltdm/0111-OMAP2-voltage-enable-VC-bypass-scale-method-when-VC-.patch \
            file://pm-wip/voltdm/0112-OMAP2-voltage-split-out-voltage-processor-VP-code-in.patch \
            file://pm-wip/voltdm/0113-OMAP2-VC-support-PMICs-with-separate-voltage-and-com.patch \
            file://pm-wip/voltdm/0114-OMAP2-add-PRM-VP-functions-for-checking-clearing-VP-.patch \
            file://pm-wip/voltdm/0115-OMAP3-VP-replace-transaction-done-check-clear-with-V.patch \
            file://pm-wip/voltdm/0116-OMAP2-PRM-add-register-access-functions-for-VC-VP.patch \
            file://pm-wip/voltdm/0117-OMAP3-voltage-convert-to-PRM-register-access-functio.patch \
            file://pm-wip/voltdm/0118-OMAP3-VC-cleanup-i2c-slave-address-configuration.patch \
            file://pm-wip/voltdm/0119-OMAP3-VC-cleanup-PMIC-register-address-configuration.patch \
            file://pm-wip/voltdm/0120-OMAP3-VC-bypass-use-fields-from-VC-struct-instead-of.patch \
            file://pm-wip/voltdm/0121-OMAP3-VC-cleanup-voltage-setup-time-configuration.patch \
            file://pm-wip/voltdm/0122-OMAP3-VC-move-on-onlp-ret-off-command-configuration-.patch \
            file://pm-wip/voltdm/0123-OMAP3-VC-abstract-out-channel-configuration.patch \
            file://pm-wip/voltdm/0124-OMAP3-voltage-domain-move-PMIC-struct-from-vdd_info-.patch \
            file://pm-wip/voltdm/0125-OMAP3-VC-make-I2C-config-programmable-with-PMIC-spec.patch \
            file://pm-wip/voltdm/0126-OMAP3-PM-VC-handle-mutant-channel-config-for-OMAP4-M.patch \
            file://pm-wip/voltdm/0127-OMAP3-VC-use-last-nominal-voltage-setting-to-get-cur.patch \
            file://pm-wip/voltdm/0128-OMAP3-VP-cleanup-move-VP-instance-into-voltdm-misc.-.patch \
            file://pm-wip/voltdm/0129-OMAP3-voltage-remove-unneeded-debugfs-interface.patch \
            file://pm-wip/voltdm/0130-OMAP3-VP-struct-omap_vp_common-replace-shift-with-__.patch \
            file://pm-wip/voltdm/0131-OMAP3-VP-move-SoC-specific-sys-clock-rate-retreival-.patch \
            file://pm-wip/voltdm/0132-OMAP3-VP-move-timing-calculation-config-into-VP-init.patch \
            file://pm-wip/voltdm/0133-OMAP3-VP-create-VP-helper-function-for-updating-erro.patch \
            file://pm-wip/voltdm/0134-OMAP3-VP-remove-omap_vp_runtime_data.patch \
            file://pm-wip/voltdm/0135-OMAP3-VP-move-voltage-scale-function-pointer-into-st.patch \
            file://pm-wip/voltdm/0136-OMAP-VP-Explicitly-mask-VPVOLTAGE-field.patch \
            file://pm-wip/voltdm/0137-OMAP3-VP-update_errorgain-return-error-if-VP.patch \
            file://pm-wip/voltdm/0138-OMAP3-VP-remove-unused-omap_vp_get_curr_volt.patch \
            file://pm-wip/voltdm/0139-OMAP3-VP-combine-setting-init-voltage-into-common-fu.patch \
            file://pm-wip/voltdm/0140-OMAP3-voltage-rename-scale-and-reset-functions-using.patch \
            file://pm-wip/voltdm/0141-OMAP3-voltage-move-rename-curr_volt-from-vdd_info-in.patch \
            file://pm-wip/voltdm/0142-OMAP3-voltdm-final-removal-of-omap_vdd_info.patch \
            file://pm-wip/voltdm/0143-OMAP3-voltage-rename-omap_voltage_get_nom_volt-voltd.patch \
            file://pm-wip/voltdm/0144-OMAP3-voltage-update-nominal-voltage-in-voltdm_scale.patch \
            file://pm-wip/voltdm/0145-OMAP4-PM-TWL6030-fix-voltage-conversion-formula.patch \
            file://pm-wip/voltdm/0146-OMAP4-PM-TWL6030-fix-uv-to-voltage-for-0x39.patch \
            file://pm-wip/voltdm/0147-OMAP4-PM-TWL6030-address-0V-conversions.patch \
            file://pm-wip/voltdm/0148-OMAP4-PM-TWL6030-fix-ON-RET-OFF-voltages.patch \
            file://pm-wip/voltdm/0149-OMAP4-PM-TWL6030-add-cmd-register.patch \
            file://pm-wip/cpufreq/0001-PM-OPP-introduce-function-to-free-cpufreq-table.patch \
            file://pm-wip/cpufreq/0002-OMAP-CPUfreq-ensure-driver-initializes-after-cpufreq.patch \
            file://pm-wip/cpufreq/0003-OMAP-CPUfreq-ensure-policy-is-fully-initialized.patch \
            file://pm-wip/cpufreq/0004-OMAP3-PM-CPUFreq-driver-for-OMAP3.patch \
            file://pm-wip/cpufreq/0005-OMAP-PM-CPUFREQ-Fix-conditional-compilation.patch \
            file://pm-wip/cpufreq/0006-cpufreq-fixup-after-new-OPP-layer-merged.patch \
            file://pm-wip/cpufreq/0007-OMAP-cpufreq-Split-OMAP1-and-OMAP2PLUS-CPUfreq-drive.patch \
            file://pm-wip/cpufreq/0008-OMAP2PLUS-cpufreq-Add-SMP-support-to-cater-OMAP4430.patch \
            file://pm-wip/cpufreq/0009-OMAP2PLUS-cpufreq-Fix-typo-when-attempting-to-set-mp.patch \
            file://pm-wip/cpufreq/0010-OMAP2-cpufreq-move-clk-name-decision-to-init.patch \
            file://pm-wip/cpufreq/0011-OMAP2-cpufreq-deny-initialization-if-no-mpudev.patch \
            file://pm-wip/cpufreq/0012-OMAP2-cpufreq-dont-support-freq_table.patch \
            file://pm-wip/cpufreq/0013-OMAP2-cpufreq-only-supports-OPP-library.patch \
            file://pm-wip/cpufreq/0014-OMAP2-cpufreq-put-clk-if-cpu_init-failed.patch \
            file://pm-wip/cpufreq/0015-OMAP2-cpufreq-fix-freq_table-leak.patch \
            file://pm-wip/cpufreq/0016-OMAP2-CPUfreq-Remove-superfluous-check-in-target-for.patch \
            file://pm-wip/cpufreq/0017-OMAP2-cpufreq-notify-even-with-bad-boot-frequency.patch \
            file://pm-wip/cpufreq/0018-OMAP2-cpufreq-Enable-all-CPUs-in-shared-policy-mask.patch \
            file://pm-wip/cpufreq/0019-OMAP2-CPUfreq-update-lpj-with-reference-value-to-avo.patch \
            \
            file://beagle/0001-OMAP3-beagle-add-support-for-beagleboard-xM-revision.patch \
            file://beagle/0002-UNFINISHED-OMAP3-beagle-add-support-for-expansionboa.patch \
            file://beagle/0003-HACK-OMAP3-beagle-switch-to-GPTIMER1.patch \
            file://beagle/0004-OMAP3-beagle-HACK-add-in-1GHz-OPP.patch \
            file://beagle/0005-omap3-Add-basic-support-for-720MHz-part.patch \
            file://beagle/0006-ARM-OMAP2-beagleboard-make-wilink-init-look-more-lik.patch \
            file://beagle/0007-omap_hsmmc-Set-dto-to-max-value-of-14-to-avoid-SD-Ca.patch \
            file://beagle/0008-OMAP2-add-cpu-id-register-to-MAC-address-helper.patch \
            file://beagle/0009-HACK-OMAP2-BeagleBoard-Fix-up-random-or-missing-MAC-.patch \
            file://beagle/0010-ARM-OMAP2-beagleboard-fix-mmc-write-protect-pin-when.patch \
            file://beagle/0011-beagleboard-reinstate-usage-of-hi-speed-PLL-divider.patch \
            file://madc/0001-Enabling-Hwmon-driver-for-twl4030-madc.patch \
            file://madc/0002-mfd-twl-core-enable-madc-clock.patch \
            \
            file://sakoman/0001-mmc-don-t-display-single-block-read-console-messages.patch \
            file://sakoman/0002-omap-Change-omap_device-activate-dectivate-latency-m.patch \
            file://sakoman/0003-OMAP-DSS2-add-bootarg-for-selecting-svideo-or-compos.patch \
            file://sakoman/0004-mtd-nand-Eliminate-noisey-uncorrectable-error-messag.patch \
            file://sakoman/0005-video-add-timings-for-hd720.patch \
            file://sakoman/0006-drivers-net-smsc911x-return-ENODEV-if-device-is-not-.patch \
            file://sakoman/0007-drivers-input-touchscreen-ads7846-return-ENODEV-if-d.patch \
            file://sakoman/0008-Revert-omap2_mcspi-Flush-posted-writes.patch \
            file://sakoman/0009-rtc-twl-Use-threaded-IRQ-remove-IRQ-enable-in-interr.patch \
            file://sakoman/0010-rtc-twl-Fix-registration-vs.-init-order.patch \
            file://sakoman/0011-soc-codecs-Enable-audio-capture-by-default-for-twl40.patch \
            file://sakoman/0012-soc-codecs-twl4030-Turn-on-mic-bias-by-default.patch \
            file://sakoman/0013-omap-mmc-twl4030-move-clock-input-selection-prior-to.patch \
            file://sakoman/0014-rtc-twl-add-support-for-backup-battery-recharge.patch \
            \
            file://sgx/0001-ARM-L2-Add-and-export-outer_clean_all.patch \
            \
            file://ulcd/0001-OMAP_VOUT-Fix-build-break-caused-by-update_mode-remo.patch \
            file://ulcd/0002-WIP-omap-beagleboard-add-bbtoys-ulcd-lite-support.patch \
            file://ulcd/0003-ARM-OMAP2-beagleboard-add-support-for-loopthrough-ex.patch \
            file://ulcd/0004-LEDS-add-initial-support-for-WS2801-controller.patch \
            \
            file://omap4/0001-OMAP-Fix-linking-error-in-twl-common.c-for-OMAP2-3-4.patch \
            \
            file://misc/0001-compiler.h-Undef-before-redefining-__attribute_const.patch \
            \
            file://usb/0001-Fix-sprz319-erratum-2.1.patch \
            \
            file://defconfig"

SRC_URI_append_beagleboard = " file://logo_linux_clut224.ppm \
"

S = "${WORKDIR}/git"

