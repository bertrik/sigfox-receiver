package nl.bertriksikken.sigfox.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SigfoxRestApi implements ISigfoxRestApi {

    private static final Logger LOG = LoggerFactory.getLogger(SigfoxRestApi.class);

    @Override
    public void uplink(String deviceTypeId, String device, String data) {
        LOG.info("Uplink from {}/{}, data: {}", deviceTypeId, device, data);
    }

}
