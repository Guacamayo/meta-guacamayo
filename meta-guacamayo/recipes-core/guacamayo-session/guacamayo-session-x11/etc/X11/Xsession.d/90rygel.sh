# FIXME -- this is obviously just a place holder, but both of these need to
# exit with session, which the currently do not
echo "Starting Pulse Audio server"
/usr/bin/pulseaudio -D --exit-idle-time=-1

echo "Starting Rygel"
exec /usr/bin/rygel
