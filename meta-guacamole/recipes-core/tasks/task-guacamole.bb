DESCRIPTION="Guacamole tasks"
LICENSE = "MIT"

LIC_FILES_CHKSUM = "file://${GUACABASE}/meta-guacamole/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r5"

PACKAGES="\
	task-guacamole-core \
	task-guacamole-restricted \
	task-guacamole-mex \
	task-guacamole-gles-tests \
	"

ALLOW_EMPTY = "1"

# The following packages might be desirable, but have restricted license, so we
# require the user to specifically flag them up as acceptable -- filter out
# these packages down to what the whitelist permits
#
# These packages have 'commercial' license flag set
GUACA_COMMERCIAL = "gst-fluendo-mp3"

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
    commercial = d.getVar('GUACA_COMMERCIAL', True)

    restricted += check_license_ok(d, commercial, 'commercial')

    d.setVar('GUACA_RESTRICTED', restricted)
}

NETWORKING = "connman \
           connman-plugin-ethernet \
           connman-plugin-loopback \
           connman-plugin-wifi \
          "

# dbus-x11 is needed for dbus-launch
RDEPENDS_task-guacamole-core = "\
			     dbus \
			     dbus-x11 \
                             ${NETWORKING} \
			     gstreamer \
			     gst-plugins-base \
			     gst-plugins-good \
			     gst-plugins-bad \
			     rygel \
			     rygel-initd \
			     gst-ffmpeg \
                               "

RDEPENDS_task-guacamole-restricted = "${GUACA_RESTRICTED}"

RDEPENDS_task-guacamole-mex = "\
			      clutter-1.10 \
			      clutter-gst-1.10	    \
			      clutter-gst-1.10-examples \
			      "

RDEPENDS_task-guacamole-gles-tests_beagleboard = "\
					       	  libgles-omap3-rawdemos \
						  clutter-1.10-examples \
					         "
