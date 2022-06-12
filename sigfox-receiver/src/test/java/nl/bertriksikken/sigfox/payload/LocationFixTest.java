package nl.bertriksikken.sigfox.payload;

import org.junit.Assert;
import org.junit.Test;

public final class LocationFixTest {

	@Test
	public void testDecode() {
		byte[] data = { 0x03, 0x19, 0x0C, (byte) 0xA0, 0x00, 0x56, (byte) 0x84, 0x11, 0x01, 0x2A, 0x08 };
		LocationFix fix = LocationFix.parse(data);

		Assert.assertEquals(51.973148, fix.getLat(), 1E-3);
		Assert.assertEquals(5.669751, fix.getLon(), 1E-3);
		Assert.assertEquals(29.8, fix.getAlt(), 1E-1);
		Assert.assertEquals(8, fix.getSats());
	}

	@Test
	public void testNegativeAltitude() {
		byte[] data = { 0x03, 0x1a, 0x77, (byte) 0xc1, 0x00, 0x48, (byte) 0xe3, (byte) 0xa6, (byte) 0xff, (byte) 0xf7,
				0x09 };
		LocationFix fix = LocationFix.parse(data);

		Assert.assertEquals(-0.9, fix.getAlt(), 0.1);
	}

}
