package nl.bertriksikken.sigfox.restapi;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * See https://support.sigfox.com/docs/data-advanced
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ComputedLocation {

    @JsonProperty("lat")
    public double latitude = Double.NaN;

    @JsonProperty("lng")
    public double longitude = Double.NaN;

    @JsonProperty("radius")
    public int radius = 0;

    @JsonProperty("source")
    public int source = 0;

    @JsonProperty("status")
    public int status = 0;

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "{lat=%f,lon=%f,radius=%d,source=%d,status=%d}", latitude, longitude, radius,
                source, status);
    }

}
