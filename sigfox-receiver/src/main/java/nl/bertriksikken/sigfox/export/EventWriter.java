package nl.bertriksikken.sigfox.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * Writes events to a CSV file.
 */
public final class EventWriter {

    private static final Logger LOG = LoggerFactory.getLogger(EventWriter.class);

    private final CsvMapper csvMapper = new CsvMapper();
    private final File eventFile;

    public EventWriter(File eventFile) {
        this.eventFile = eventFile;
    }

    public void write(LocationEvent event) throws IOException {
        boolean append = eventFile.exists();
        CsvSchema schema = csvMapper.schemaFor(event.getClass());
        schema = append ? schema.withoutHeader() : schema.withHeader();
        ObjectWriter writer = csvMapper.writer(schema);
        try (FileOutputStream fos = new FileOutputStream(eventFile, append)) {
            String value = writer.writeValueAsString(event).trim();
            LOG.info("Writing to {}: {}", eventFile.getName(), value);
            writer.writeValue(fos, event);
        }
    }

}
