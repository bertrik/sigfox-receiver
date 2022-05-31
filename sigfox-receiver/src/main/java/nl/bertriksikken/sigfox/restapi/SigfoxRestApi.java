package nl.bertriksikken.sigfox.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SigfoxRestApi implements ISigfoxRestApi {

    private static final Logger LOG = LoggerFactory.getLogger(SigfoxRestApi.class);

    @Override
    public void uplink(String data) {
        LOG.info("Uplink, data: {}", data);
    }

    @Override
    public void advanced(String data) {
        LOG.info("Data advanced, data: {}", data);
    }

    @Override
    public void status(String data) {
        LOG.info("Status, data: {}", data);
    }

}
