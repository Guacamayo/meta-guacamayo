# FIXME -- this is obviously just a place holder, but both of these need to
# exit with session, which the currently do not
echo "Starting Pulse Audio server"
/usr/bin/pulseaudio -D --exit-idle-time=-1

# When we get this properly sorted, we will make MEX to be the last execed
# but for now put it here, so that when it does not work, we still get
# X session running
echo "Starting MEX"
media-explorer -f

echo "Starting Rygel"
exec /usr/bin/rygel

