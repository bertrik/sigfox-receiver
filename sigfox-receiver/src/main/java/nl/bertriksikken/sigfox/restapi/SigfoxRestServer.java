package nl.bertriksikken.sigfox.restapi;

import jakarta.ws.rs.core.UriBuilder;
import nl.bertriksikken.sigfox.SigfoxRestHandler;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;

public final class SigfoxRestServer {

    private static final Logger LOG = LoggerFactory.getLogger(SigfoxRestServer.class);

    private final Server server;

    public SigfoxRestServer(SigfoxRestConfig config, SigfoxRestHandler handler) {
        SigfoxRestServlet.setHandler(handler);
        this.server = createRestServer(config.getPort(), config.getPath(), SigfoxRestServlet.class);
    }

    public void start() throws IOException {
        LOG.info("Starting REST server");
        try {
            server.start();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public void stop() {
        LOG.info("Stopping REST server");
        try {
            server.stop();
        } catch (Exception e) {
            LOG.error("Caught exception during shutdown: {}", e.getMessage());
            LOG.trace("Caught exception during shutdown", e);
        }
    }

    private Server createRestServer(int port, @SuppressWarnings("UnusedVariable") String contextPath, Class<?> clazz) {
        LOG.info("Setting up SigFox REST service on {}", port);
        URI uri = UriBuilder.fromUri("http://localhost").port(port).build();
        ResourceConfig config = new ResourceConfig(clazz);
        return JettyHttpContainerFactory.createServer(uri, config);
    }

}
