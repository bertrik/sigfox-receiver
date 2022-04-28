package nl.bertriksikken.sigfox.restapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * See https://support.sigfox.com/docs/uplink
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Uplink {

    @JsonProperty("time")
    int time;

    @JsonProperty("deviceTypeId")
    String deviceTypeId;

    @JsonProperty("device")
    String device;

    @JsonProperty("rssi")
    double rssi;

    @JsonProperty("station")
    String station;

    @JsonProperty("data")
    String data;

    @JsonProperty("seqNumber")
    int seqNumber;

}
