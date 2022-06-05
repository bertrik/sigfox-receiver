package nl.bertriksikken.sigfox.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.bertriksikken.sigfox.SigfoxRestHandler;

/**
 * Sigfox REST API servlet, passes calls on to the handler.
 */
final class SigfoxRestServlet implements ISigfoxRestApi {

    private static final Logger LOG = LoggerFactory.getLogger(SigfoxRestServlet.class);

    // single instance of the handler
    private static SigfoxRestHandler handler;

    static void setHandler(SigfoxRestHandler sigfoxRestHandler) {
        handler = sigfoxRestHandler;
    }

    @Override
    public void uplink(String userAgent, String data) {
        LOG.info("Uplink, data from {}: {}", userAgent, data);
    }

    @Override
    public void advanced(String userAgent, DataAdvanced data) {
        LOG.info("Data advanced, data from {}: {}", userAgent, data);
        // pass onto the handler
        handler.advanced(data);
    }

    @Override
    public void status(String userAgent, String data) {
        LOG.info("Status, data: {}", data);
    }

}
