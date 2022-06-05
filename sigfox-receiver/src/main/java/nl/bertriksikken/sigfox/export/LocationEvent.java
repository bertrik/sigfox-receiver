package nl.bertriksikken.sigfox.export;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Export event, one line in a CSV file.
 */
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "time", "device", "sequence", "data", "link", "atlas_lat", "atlas_lon", "atlas_radius",
        "atlas_source", "atlas_status", "gps_lat", "gps_lon", "gps_alt", "gps_sats" })
public final class LocationEvent {

    @JsonProperty("time")
    private final String time;

    @JsonProperty("device")
    private final String device;

    @JsonProperty("sequence")
    private final int sequence;

    @JsonProperty("data")
    private final String data;

    @JsonProperty("link")
    private final int link;

    @JsonProperty("atlas_lat")
    BigDecimal atlasLat;

    @JsonProperty("atlas_lon")
    BigDecimal atlasLon;

    @JsonProperty("atlas_radius")
    Integer atlasRadius;

    @JsonProperty("atlas_source")
    Integer atlasSource;

    @JsonProperty("atlas_status")
    Integer atlasStatus;

    @JsonProperty("gps_lat")
    BigDecimal gpsLat;

    @JsonProperty("gps_lon")
    BigDecimal gpsLon;

    @JsonProperty("gps_alt")
    BigDecimal gpsAlt;

    @JsonProperty("gps_sats")
    Integer gpsSats;

    public LocationEvent(Instant time, String device, int sequence, String data, int link) {
        this.time = time.truncatedTo(ChronoUnit.SECONDS).toString();
        this.device = device;
        this.sequence = sequence;
        this.data = data;
        this.link = link;
    }

    public void setAtlas(double latitude, double longitude, int radius, int source, int status) {
        atlasLat = BigDecimal.valueOf(latitude).setScale(6, RoundingMode.HALF_UP);
        atlasLon = BigDecimal.valueOf(longitude).setScale(6, RoundingMode.HALF_UP);
        atlasRadius = radius;
        atlasSource = source;
        atlasStatus = status;
    }

    public void setGps(double latitude, double longitude, double altitude, int sats) {
        gpsLat = BigDecimal.valueOf(latitude).setScale(6, RoundingMode.HALF_UP);
        gpsLon = BigDecimal.valueOf(longitude).setScale(6, RoundingMode.HALF_UP);
        gpsAlt = BigDecimal.valueOf(altitude).setScale(2, RoundingMode.HALF_UP);
        gpsSats = sats;
    }

    private String formatAtlas() {
        return atlasLat == null ? ""
                : String.format(Locale.ROOT, "{lat=%.6f,lon=%.6f,radius=%d,source=%d,status=%d}", atlasLat, atlasLon,
                        atlasRadius, atlasSource, atlasStatus);
    }

    private String formatGps() {
        return gpsLat == null ? ""
                : String.format(Locale.ROOT, "{lat=%.6f,lon=%.6f,alt=%.1f,sats=%d}", gpsLat, gpsLon, gpsAlt, gpsSats);
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "{time=%s,device=%s,sequence=%d,data=%s,atlas=%s,gps=%s}", time, device,
                sequence, data, formatAtlas(), formatGps());
    }

}
