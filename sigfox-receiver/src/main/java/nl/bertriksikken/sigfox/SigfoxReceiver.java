package nl.bertriksikken.sigfox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import nl.bertriksikken.sigfox.export.EventWriter;
import nl.bertriksikken.sigfox.restapi.SigfoxRestServer;

public final class SigfoxReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(SigfoxReceiver.class);

    private final SigfoxRestServer restServer;

    public SigfoxReceiver(SigfoxReceiverConfig config) {
        EventWriter eventWriter = new EventWriter(new File(config.getEventFile()));
        SigfoxRestHandler handler = new SigfoxRestHandler(eventWriter);
        this.restServer = new SigfoxRestServer(config.getRestConfig(), handler);
    }

    private static SigfoxReceiverConfig readOrCreateConfig(File configFile) throws IOException {
        SigfoxReceiverConfig defaultConfig = new SigfoxReceiverConfig();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        if (configFile.exists()) {
            try (FileInputStream fis = new FileInputStream(configFile)) {
                return mapper.readValue(fis, SigfoxReceiverConfig.class);
            } catch (IOException e) {
                LOG.warn("Failed to load config {}, using defaults", configFile.getAbsoluteFile());
            }
        } else {
            LOG.warn("Config {} does not exist, writing defaults", configFile.getAbsoluteFile());
            mapper.writeValue(configFile, defaultConfig);
        }
        return defaultConfig;
    }

    private void start() throws IOException {
        LOG.info("Starting SigfoxReceiver");
        restServer.start();
    }

    private void stop() {
        LOG.info("Stopping SigfoxReceiver");
        restServer.stop();
    }

    public static void main(String[] args) throws IOException {
        PropertyConfigurator.configure("log4j.properties");
        SigfoxReceiverConfig config = readOrCreateConfig(new File("sigfox-receiver.yaml"));
        SigfoxReceiver app = new SigfoxReceiver(config);
        Runtime.getRuntime().addShutdownHook(new Thread(app::stop));
        app.start();
    }

}
