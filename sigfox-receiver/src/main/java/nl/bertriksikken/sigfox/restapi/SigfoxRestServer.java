package nl.bertriksikken.sigfox.restapi;

import java.io.IOException;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.bertriksikken.sigfox.SigfoxRestHandler;

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

    private Server createRestServer(int port, String contextPath, Class<?> clazz) {
        LOG.info("Setting up SigFox REST service on {}", port);
        Server server = new Server(port);

        // setup context
        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);

        // suppress sending the exact jetty version
        for (Connector connector : server.getConnectors()) {
            connector.getConnectionFactories().stream().filter(HttpConnectionFactory.class::isInstance)
                    .map(HttpConnectionFactory.class::cast)
                    .forEach(f -> f.getHttpConfiguration().setSendServerVersion(false));
        }

        // setup web services container
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter(ServerProperties.PROVIDER_CLASSNAMES, clazz.getCanonicalName());
        context.addServlet(sh, contextPath + "/*");
        server.setHandler(context);
        return server;
    }

}
