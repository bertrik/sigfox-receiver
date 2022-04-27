package nl.bertriksikken.sigfox.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class SigfoxRestApiConfig {

    @JsonProperty("port")
    private int port = 9001;
    @JsonProperty("path")
    private String path = "";
    
    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

}
