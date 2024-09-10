package nl.bertriksikken.sigfox;

import java.io.IOException;
import java.time.Instant;
import java.util.Base64;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        byte[] data = Base64.getDecoder().decode(advanced.data.toUpperCase(Locale.ROOT));
        LocationFix gps = LocationFix.parse(data);
        if (gps != null) {
            event.setGps(gps.getLat(), gps.getLon(), gps.getAlt(), gps.getSats());
        }

        try {
            eventWriter.write(event);
        } catch (IOException e) {
            LOG.warn("Could not write event: {}", event, e);
        }
    }

}
