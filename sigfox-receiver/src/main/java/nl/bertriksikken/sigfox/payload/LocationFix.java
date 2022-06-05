package nl.bertriksikken.sigfox.payload;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Locale;

/**
 * A location fix, as sent by my sigfox tracker.<br>
 */
public final class LocationFix {

    private final double lat;
    private final double lon;
    private final double alt;
    private final int sats;

    LocationFix(double lat, double lon, double alt, int sats) {
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
        this.sats = sats;
    }

    public static LocationFix parse(byte[] data) {
        try {
            ByteBuffer bb = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN);
            double lat = 1E-6 * bb.getInt();
            double lon = 1E-6 * bb.getInt();
            double alt = 1E-1 * bb.getShort();
            int sats = bb.get() & 0xFF;
            return new LocationFix(lat, lon, alt, sats);
        } catch (BufferUnderflowException e) {
            return null;
        }
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double getAlt() {
        return alt;
    }

    public int getSats() {
        return sats;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "{lat=%f,lon=%f,alt=%d,sats=%d}", lat, lon, alt, sats);
    }
}
