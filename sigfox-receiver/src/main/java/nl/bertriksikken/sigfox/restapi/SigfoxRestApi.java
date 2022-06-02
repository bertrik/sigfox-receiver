package nl.bertriksikken.sigfox.restapi;

import javax.ws.rs.HeaderParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SigfoxRestApi implements ISigfoxRestApi {

    private static final Logger LOG = LoggerFactory.getLogger(SigfoxRestApi.class);

    @Override
    public void uplink(String userAgent, String data) {
        LOG.info("Uplink, data from {}: {}", userAgent, data);
    }

    @Override
    public void advanced(String userAgent, String data) {
        LOG.info("Data advanced, data from {}: {}", userAgent, data);
    }

    @Override
    public void status(String userAgent, String data) {
        LOG.info("Status, data: {}", data);
    }

}
