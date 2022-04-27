package nl.bertriksikken.sigfox;

import com.fasterxml.jackson.annotation.JsonProperty;

import nl.bertriksikken.sigfox.restapi.SigfoxRestApiConfig;

public final class SigfoxReceiverConfig {

    @JsonProperty("restapi")
    private SigfoxRestApiConfig restApiConfig = new SigfoxRestApiConfig();
    
    public SigfoxRestApiConfig getRestApiConfig() {
        return restApiConfig;
    }
    
}
