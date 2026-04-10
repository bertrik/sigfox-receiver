package nl.bertriksikken.sigfox.export;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public final class ExportEventTest {

    @Test
    public void testSerializeMinimal() throws JsonProcessingException {
        LocationEvent event = new LocationEvent(Instant.now(), "device", 123, "AABBCC", 0);
        CsvMapper mapper = new CsvMapper();

        CsvSchema schema = mapper.schemaFor(event.getClass());
        ObjectWriter writer = mapper.writer(schema);
        String value = writer.writeValueAsString(event);

        System.out.println(value);
    }

}
