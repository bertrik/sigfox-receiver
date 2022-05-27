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
    double latitude = Double.NaN;

    @JsonProperty("lng")
    double longitude = Double.NaN;

    @JsonProperty("radius")
    int radius = 0;

    @JsonProperty("source")
    int source = 0;

    @JsonProperty("status")
    int status = 0;

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "{lat=%f,lon=%f,radius=%d,source=%d,status=%d}", latitude, longitude, radius,
                source, status);
    }

}
