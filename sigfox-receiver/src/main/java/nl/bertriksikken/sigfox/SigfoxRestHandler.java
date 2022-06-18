package nl.bertriksikken.sigfox;

import java.io.IOException;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.BaseEncoding;

import nl.bertriksikken.sigfox.export.EventWriter;
import nl.bertriksikken.sigfox.export.LocationEvent;
import nl.bertriksikken.sigfox.payload.LocationFix;
import nl.bertriksikken.sigfox.restapi.ComputedLocation;
import nl.bertriksikken.sigfox.restapi.DataAdvanced;

/**
 * Sigfox REST handler.
 */
public final class SigfoxRestHandler {

    private static final Logger LOG = LoggerFactory.getLogger(SigfoxRestHandler.class);

    private final EventWriter eventWriter;

    SigfoxRestHandler(EventWriter eventWriter) {
        this.eventWriter = eventWriter;
    }

    public void advanced(DataAdvanced advanced) {
        Instant time = Instant.ofEpochSecond(advanced.time);
        LocationEvent event = new LocationEvent(time, advanced.device, advanced.seqNumber, advanced.data,
                advanced.linkQuality);

        // add Atlas location fix
        ComputedLocation atlas = advanced.computedLocation;
        if (atlas != null) {
            event.setAtlas(atlas.latitude, atlas.longitude, atlas.radius, atlas.source, atlas.status);
        }

        // add GPS location fix
        byte[] data = BaseEncoding.base16().decode(advanced.data.toUpperCase());
        LocationFix gps = LocationFix.parse(data);
        if (gps != null) {
            event.setGps(gps.lat, gps.lon, gps.alt, gps.sats);
        }

        // calculate distance between Atlas and GPS
        if ((atlas != null) && (gps != null)) {
            double distance = GeoUtils.calculateDistance(new double[] { gps.lat, gps.lon },
                    new double[] { atlas.latitude, atlas.longitude });
            event.setDistance(distance);
        }

        try {
            eventWriter.write(event);
        } catch (IOException e) {
            LOG.warn("Could not write event: {}", event, e);
        }
    }

}
