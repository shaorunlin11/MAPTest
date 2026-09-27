package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class LocationsetLongitudeTest {
    private Location location;

    @Before
    public void setUp() {
        location = new Location();
    }

    @Test
    public void testSetLongitude() throws Exception {
        double expectedLongitude = -123.456;
        location.setLongitude(expectedLongitude);

        Field longitudeField = Location.class.getDeclaredField("longitude");
        longitudeField.setAccessible(true);
        Double actualLongitude = (Double) longitudeField.get(location);

        assertEquals(expectedLongitude, actualLongitude, 0.0001);
    }
}
