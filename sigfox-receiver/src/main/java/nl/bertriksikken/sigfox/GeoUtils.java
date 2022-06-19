package nl.bertriksikken.sigfox;

public final class GeoUtils {

    private static final double CIRCUMFERENCE = 40075E3;

    /**
     * Calculates simplified distance, between two {lat, lon} coordinates.<br>
     * Works well for relatively small distances (< 100 km).
     */
    public static double calculateDistance(double[] p1, double[] p2) {
        double meterPerDegreeLat = CIRCUMFERENCE / 360.0;
        double meterPerDegreeLon = meterPerDegreeLat * Math.cos(Math.toRadians(p1[0]));

        double dx = meterPerDegreeLon * (p1[1] - p2[1]);
        double dy = meterPerDegreeLat * (p1[0] - p2[0]);

        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }

}
