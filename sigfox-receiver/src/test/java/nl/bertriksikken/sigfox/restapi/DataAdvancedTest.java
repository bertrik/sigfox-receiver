package nl.bertriksikken.sigfox.restapi;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class DataAdvancedTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    /**
     * Tests (de)serialization.
     */
    @Test
    public void testSerialize() throws IOException {
        URL url = getClass().getResource("/data_advanced.json");
        DataAdvanced dataAdvanced = MAPPER.readValue(url, DataAdvanced.class);
        
        assertEquals("1FD48BE", dataAdvanced.device);
        assertEquals(1653666733, dataAdvanced.time);
        assertEquals("00", dataAdvanced.data);
        assertEquals(16, dataAdvanced.seqNumber);
        assertEquals("Limit", dataAdvanced.lqi);
        assertEquals(0, dataAdvanced.linkQuality);
        assertEquals(0.0, dataAdvanced.fixedLat, 1E-6);
        assertEquals(0.0, dataAdvanced.fixedLng, 1E-6);
        assertEquals("SIGFOX_Netherlands_Hyrde_Networks_BV", dataAdvanced.operatorName);
        assertEquals(528, dataAdvanced.countryCode);
        assertEquals("628fd74fb3cabb6565ae81bb", dataAdvanced.deviceTypeId);
        
        ComputedLocation computedLocation = dataAdvanced.computedLocation;
        Assertions.assertNotNull(computedLocation);
        assertEquals(52.003292529645705, computedLocation.latitude, 1E-6);
        assertEquals(5.569001982230841, computedLocation.longitude, 1E-6);
        assertEquals(7800, computedLocation.radius);
        assertEquals(2, computedLocation.source);
        assertEquals(1, computedLocation.status);
        
        String s = dataAdvanced.toString();
        System.out.println(s);
    }
    
}
