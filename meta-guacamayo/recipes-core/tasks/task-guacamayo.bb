DESCRIPTION="Guacamayo tasks"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamayo/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r27"

DEPENDS += "alsa-plugins"

PACKAGES="\
	task-guacamayo			\
	task-guacamayo-core		\
	task-guacamayo-renderer		\
	task-guacamayo-restricted-core	\
	task-guacamayo-restricted-audio	\
	task-guacamayo-restricted-video	\
	task-guacamayo-devtools		\
	task-guacamayo-demos-audio	\
	task-guacamayo-demos-video	\
	task-guacamayo-demos-pictures	\
	task-guacamayo-server		\
	"

RDEPENDS_task-guacamayo = ""

ALLOW_EMPTY = "1"

# The following packages might be desirable, but have restricted license, so we
# require the user to specifically flag them up as acceptable -- filter out
# these packages down to what the whitelist permits
#
# These packages have 'commercial' license flag set
GUACA_COMMERCIAL_CORE  = "connman-guacamayo-ntp"
GUACA_COMMERCIAL_AUDIO = ""
GUACA_COMMERCIAL_VIDEO = "gst-fluendo-mp3"

python __anonymous () {
    def license_flag_matches(flag, whitelist, pn):
        """
	Borrowed from license.bbclass, unomodified
        Return True if flag matches something in whitelist, None if not.
        """
        flag_pn = ("%s_%s" % (flag, pn))
        for candidate in whitelist:
            if flag_pn == candidate:
                    return True

        flag_cur = ""
        flagments = flag_pn.split("_")
        flagments.pop() # we've already tested the full string
        for flagment in flagments:
            if flag_cur:
                flag_cur += "_"
            flag_cur += flagment
            for candidate in whitelist:
                if flag_cur == candidate:
                    return True
        return False

    def check_license_ok(d, pns, license_flag):
        """
        Filters list of packages names pns to those that are permitted for
        the given license_flag
        """
	good = ""

        whitelist = d.getVar('LICENSE_FLAGS_WHITELIST', True)

        if not whitelist:
	    bb.warn ("The following packages will not be included because their restrictive license is not whitelisted: %s; you can enable them by including LICENSE_FLAGS_WHITELIST in your local.conf, see Yocto documentation for LICENSE_FLAGS_WHITELIST" % pns)
            return ""

	split_whitelist = whitelist.split()

        for pn in pns.split():
            if license_flag_matches(license_flag, split_whitelist, pn):
                good += ' ' + pn
	    else:
	        bb.warn ("Package %s will not be included because its restrictive license is not whitelisted; you can enable it by including LICENSE_FLAGS_WHITELIST in your local.conf, see Yocto documentation for LICENSE_FLAGS_WHITELIST" % pn)

        return good

    restricted = ""
    commercial = d.getVar('GUACA_COMMERCIAL_CORE', True)
    restricted += check_license_ok(d, commercial, 'commercial')
    d.setVar('GUACA_RESTRICTED_CORE', restricted)

    restricted = ""
    commercial = d.getVar('GUACA_COMMERCIAL_AUDIO', True)
    restricted += check_license_ok(d, commercial, 'commercial')
    d.setVar('GUACA_RESTRICTED_AUDIO', restricted)

    restricted = ""
    commercial = d.getVar('GUACA_COMMERCIAL_VIDEO', True)
    restricted += check_license_ok(d, commercial, 'commercial')
    d.setVar('GUACA_RESTRICTED_VIDEO', restricted)
}

GUACA_NETWORKING = "connman-initd \
                    connman-plugin-ethernet \
                    connman-plugin-loopback \
                    connman-plugin-wifi \
                   "

GUACA_PA_CORE = "pulseaudio \
                 pulseaudio-server \
                 gst-plugins-good-pulse \
		 libasound-module-conf-pulse \
		 libasound-module-ctl-pulse \
		 libasound-module-pcm-pulse \
		"

GUACA_DEVTOOLS += "pulseaudio-misc \
		   dbus-daemon-proxy \
		  "

GUACA_DEMOS_PICTURES = "guacamayo-demos-pictures"
GUACA_DEMOS_AUDIO    = "guacamayo-demos-audio"
GUACA_DEMOS_VIDEO    = "guacamayo-demos-video"

# dbus-x11 is needed for dbus-launch (fixed in oe-core master)
RDEPENDS_task-guacamayo-core = "			\
			     tzdata			\
			     dconf			\
			     guacamayo-gsettings	\
			     dbus			\
			     dbus-x11			\
                             ${GUACA_NETWORKING}	\
			     gstreamer			\
			     gst-plugins-base		\
			     gst-plugins-good		\
			     gst-plugins-bad		\
			     gst-meta-audio		\
			     gst-meta-video		\
			     gst-plugins-base-meta	\
			     gst-plugins-good-meta	\
			     gst-plugins-bad-meta	\
			     gst-plugins-good-id3demux	\
			     gst-plugins-bad-id3tag	\
			     gst-ffmpeg			\
			     rygel			\
			     shared-mime-info		\
			     "

RDEPENDS_task-guacamayo-core_append_raspberrypi = "rpi-zram-service-initd"

RDEPENDS_task-guacamayo-renderer = " \
			     ${GUACA_PA_CORE} \
			     rygel-plugin-playbin \
			     "

RDEPENDS_task-guacamayo-server = " \
			     rygel-plugin-media-export \
			     "

RDEPENDS_task-guacamayo-restricted-core  = "${GUACA_RESTRICTED_CORE}"
RDEPENDS_task-guacamayo-restricted-audio = "${GUACA_RESTRICTED_AUDIO}"
RDEPENDS_task-guacamayo-restricted-video = "${GUACA_RESTRICTED_VIDEO}"

RDEPENDS_task-guacamayo-devtools = "${GUACA_DEVTOOLS}"

RDEPENDS_task-guacamayo-demos-pictures = "${GUACA_DEMOS_PICTURES}"
RDEPENDS_task-guacamayo-demos-audio    = "${GUACA_DEMOS_AUDIO}"
RDEPENDS_task-guacamayo-demos-video    = "${GUACA_DEMOS_VIDEO}"
