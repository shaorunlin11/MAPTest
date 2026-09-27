package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class LocationgetLatitudeTest {
    private Location location;

    @Before
    public void setUp() {
        location = new Location();
    }

    @Test
    public void testGetLatitudeReturnsInitializedValue() throws Exception {
        double expectedLatitude = 40.7128;
        Field latitudeField = Location.class.getDeclaredField("latitude");
        latitudeField.setAccessible(true);
        latitudeField.set(location, expectedLatitude);

        double actualLatitude = location.getLatitude();
        assertEquals(expectedLatitude, actualLatitude, 0.0001);
    }
}
