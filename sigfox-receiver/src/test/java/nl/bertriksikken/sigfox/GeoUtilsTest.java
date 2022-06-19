package nl.bertriksikken.sigfox;

import org.junit.Assert;
import org.junit.Test;

public final class GeoUtilsTest {

    @Test
    public void testDistance() {
        double[] rotterdam = new double[] { 51.9279653, 4.420789 };
        double[] groningen = new double[] { 53.2216999, 6.5306741 };

        double distance = GeoUtils.calculateDistance(rotterdam, groningen);
        Assert.assertEquals(204249, distance, 100);
    }

}
