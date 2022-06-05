package nl.bertriksikken.sigfox;

import com.fasterxml.jackson.annotation.JsonProperty;

import nl.bertriksikken.sigfox.restapi.SigfoxRestConfig;

public final class SigfoxReceiverConfig {

    @JsonProperty("rest")
    private SigfoxRestConfig restConfig = new SigfoxRestConfig();

    @JsonProperty("eventFile")
    private String eventFile = "events.csv";

    public SigfoxRestConfig getRestConfig() {
        return restConfig;
    }

    public String getEventFile() {
        return eventFile;
    }

}
