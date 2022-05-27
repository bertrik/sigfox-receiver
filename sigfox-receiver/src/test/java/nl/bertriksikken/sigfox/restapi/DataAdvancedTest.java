package nl.bertriksikken.sigfox.restapi;

import java.io.IOException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class DataAdvancedTest {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    /**
     * Tests (de)serialization.
     */
    @Test
    public void testSerialize() throws IOException {
        URL url = getClass().getResource("/data_advanced.json");
        DataAdvanced dataAdvanced = MAPPER.readValue(url, DataAdvanced.class);
        
        Assert.assertEquals("1FD48BE", dataAdvanced.device);
        Assert.assertEquals(1653666733, dataAdvanced.time);
        Assert.assertEquals("00", dataAdvanced.data);
        Assert.assertEquals(16, dataAdvanced.seqNumber);
        Assert.assertEquals("Limit", dataAdvanced.lqi);
        Assert.assertEquals(0, dataAdvanced.linkQuality);
        Assert.assertEquals(0.0, dataAdvanced.fixedLat, 0.01);
        Assert.assertEquals(0.0, dataAdvanced.fixedLng, 0.01);
        Assert.assertEquals("SIGFOX_Netherlands_Hyrde_Networks_BV", dataAdvanced.operatorName);
        Assert.assertEquals(528, dataAdvanced.countryCode);
        Assert.assertEquals("628fd74fb3cabb6565ae81bb", dataAdvanced.deviceTypeId);
        
        ComputedLocation computedLocation = dataAdvanced.computedLocation;
        Assert.assertNotNull(computedLocation);
    }
    
}
