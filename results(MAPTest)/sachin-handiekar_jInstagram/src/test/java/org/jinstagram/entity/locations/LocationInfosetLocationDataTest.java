package org.jinstagram.entity.locations;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Location;

public class LocationInfosetLocationDataTest {
    private LocationInfo locationInfo;
    private Location location;

    @Before
    public void setUp() {
        locationInfo = new LocationInfo();
        location = new Location();
    }

    @After
    public void tearDown() {
        locationInfo = null;
        location = null;
    }

    @Test
    public void testSetLocationData() throws Exception {
        // Act
        locationInfo.setLocationData(location);

        // Assert
        Assert.assertEquals(location, getLocationDataFromLocationInfo(locationInfo));
    }

    private Location getLocationDataFromLocationInfo(LocationInfo locationInfo) throws Exception {
        java.lang.reflect.Field field = LocationInfo.class.getDeclaredField("locationData");
        field.setAccessible(true);
        return (Location) field.get(locationInfo);
    }
}
