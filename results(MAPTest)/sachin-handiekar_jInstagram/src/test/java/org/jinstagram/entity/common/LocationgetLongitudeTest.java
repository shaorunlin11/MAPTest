package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class LocationgetLongitudeTest {

    @Test
    public void testGetLongitude() throws Exception {
        Location location = new Location();
        double expectedLongitude = 123.456;
        Field longitudeField = Location.class.getDeclaredField("longitude");
        longitudeField.setAccessible(true);
        longitudeField.set(location, expectedLongitude);

        double result = location.getLongitude();
        assertEquals(expectedLongitude, result, 0.0);
    }
}
