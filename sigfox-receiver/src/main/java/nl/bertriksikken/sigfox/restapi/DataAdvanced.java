package nl.bertriksikken.sigfox.restapi;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representation of JSON object for "Data advanced" callback.<br>
 * Assumes the following settings in the backend:
 * <li>HTTP method: POST
 * <li>Content-type: application/json
 * <li>Body:
 * 
 * <pre>
 * {
  "device": "{device}",
  "time": {time},
  "data": "{data}",
  "seqNumber": {seqNumber},
  "lqi": "{lqi}",
  "linkQuality": "{linkQuality}",
  "fixedLat": "{fixedLat}",
  "fixedLng": "{fixedLng}",
  "operatorName": "{operatorName}",
  "countryCode": "{countryCode}",
  "deviceTypeId": "{deviceTypeId}",
  "computedLocation": {computedLocation}
  }
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class DataAdvanced {

    @JsonProperty("device")
    public String device = "";

    @JsonProperty("time")
    public long time = 0;

    @JsonProperty("data")
    public String data = "";

    @JsonProperty("seqNumber")
    public int seqNumber = 0;

    @JsonProperty("lqi")
    String lqi = ""; // e.g. "Limit"

    @JsonProperty("linkQuality")
    public int linkQuality = 0; // e.g. 0

    @JsonProperty("fixedLat")
    Double fixedLat = Double.NaN;

    @JsonProperty("fixedLng")
    Double fixedLng = Double.NaN;

    @JsonProperty("operatorName")
    String operatorName = "";

    @JsonProperty("countryCode")
    int countryCode = 0; // e.g. 528

    @JsonProperty("deviceTypeId")
    String deviceTypeId = ""; // e.g. 628fd74fb3cabb6565ae81bb

    @JsonProperty("computedLocation")
    public ComputedLocation computedLocation = new ComputedLocation();

    @Override
    public String toString() {
        return String.format(Locale.ROOT,
                "{device=%s,time=%d,data=%s,seqNumber=%d,lqi=%s,linkQuality=%d,fixed={%f,%f},operatorName=%s,"
                        + "countryCode=%d,deviceTypeId=%s,computedLocation=%s}",
                device, time, data, seqNumber, lqi, linkQuality, fixedLat, fixedLng, operatorName, countryCode,
                deviceTypeId, computedLocation);
    }

}
