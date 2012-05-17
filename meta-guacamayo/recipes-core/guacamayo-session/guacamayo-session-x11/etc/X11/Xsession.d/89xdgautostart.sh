XDGAUTOSTART=/etc/xdg/autostart
if [ -d $XDGAUTOSTART ]; then
    for SCRIPT in $XDGAUTOSTART/*; do
        CMD=`grep Exec= $SCRIPT | sed -e s/.*Exec=// -e s/%.//g`
        $CMD &
    done
fi
