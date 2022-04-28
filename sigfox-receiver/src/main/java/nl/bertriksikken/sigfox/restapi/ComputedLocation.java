package nl.bertriksikken.sigfox.restapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * See https://support.sigfox.com/docs/data-advanced
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ComputedLocation {

    @JsonProperty("lat")
    double latitude;

    @JsonProperty("lng")
    double longitude;

    @JsonProperty("radius")
    int radius;

    @JsonProperty("source")
    int source;

    @JsonProperty("status")
    int status;

}
