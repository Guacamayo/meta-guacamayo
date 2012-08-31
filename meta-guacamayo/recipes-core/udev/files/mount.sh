#!/bin/sh
#
# Called from udev
#
# Attempt to mount any added block devices and umount any removed devices


MOUNT="/bin/mount"
PMOUNT="/usr/bin/pmount"
UMOUNT="/bin/umount"

for line in `cat /etc/udev/mount.blacklist`
do
	if [ ` expr match "$DEVNAME" "$line" ` -gt 0 ];
	then
		logger "udev/mount.sh" "[$DEVNAME] is blacklisted, ignoring"
		exit 0
	fi
done

automount() {
	name="`basename "$DEVNAME"`"

	! test -d "/media/$name" && mkdir -p "/media/$name"

	if ! $MOUNT -t auto -o sync $DEVNAME "/media/$name"
	then
		#logger "mount.sh/automount" "$MOUNT -t auto $DEVNAME \"/media/$name\" failed!"
		rm_dir "/media/$name"
	else
		if test "/media/$name/etc/udev/udev.conf" -ef "/etc/udev/udev.conf"
		    #oh boy, we have just mounted the rootfs again ...
		    logger "mount.sh/automount" "$DEVICE is the root device, unmounting from /media/$name"
		    $UMOUNT "/media/$name"
		then
		else
		    logger "mount.sh/automount" "Auto-mount of [/media/$name] successful"
		    touch "/tmp/.automount-$name"
		fi
	fi
}

rm_dir() {
	# We do not want to rm -r populated directories
	if test "`find "$1" | wc -l | tr -d " "`" -lt 2 -a -d "$1"
	then
		! test -z "$1" && rm -r "$1"
	else
		logger "mount.sh/automount" "Not removing non-empty directory [$1]"
	fi
}

if [ "$ACTION" = "add" ] && [ -n "$DEVNAME" ]; then
	if [ -x "$PMOUNT" ]; then
		$PMOUNT $DEVNAME 2> /dev/null
	elif [ -x $MOUNT ]; then
    		$MOUNT $DEVNAME 2> /dev/null
	fi

	# If the device isn't mounted at this point, it isn't configured in fstab
	grep -q "^$DEVNAME " /proc/mounts || automount
fi



if [ "$ACTION" = "remove" ] && [ -x "$UMOUNT" ] && [ -n "$DEVNAME" ]; then
	for mnt in `cat /proc/mounts | grep "$DEVNAME" | cut -f 2 -d " " `
	do
		$UMOUNT $mnt
	done

	# Remove empty directories from auto-mounter
	name="`basename "$DEVNAME"`"
	test -e "/tmp/.automount-$name" && rm_dir "/media/$name"
fi
