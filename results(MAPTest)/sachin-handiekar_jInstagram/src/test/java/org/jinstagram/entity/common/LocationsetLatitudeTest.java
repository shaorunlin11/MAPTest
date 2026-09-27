package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.lang.reflect.Field;

public class LocationsetLatitudeTest {
    private Location location;
    private Field latitudeField;

    @Before
    public void setUp() throws Exception {
        location = new Location();
        latitudeField = Location.class.getDeclaredField("latitude");
        latitudeField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        location = null;
        latitudeField = null;
    }

    @Test
    public void testSetLatitude() throws Exception {
        double expectedLatitude = 40.7128;
        location.setLatitude(expectedLatitude);

        double actualLatitude = ((Double) latitudeField.get(location)).doubleValue();
        Assert.assertEquals("Latitude should be set correctly", expectedLatitude, actualLatitude, 0.0001);
    }
}
