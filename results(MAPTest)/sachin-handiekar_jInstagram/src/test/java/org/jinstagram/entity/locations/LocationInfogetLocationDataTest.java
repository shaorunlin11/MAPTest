package org.jinstagram.entity.locations;

import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Location;
import com.google.gson.annotations.SerializedName;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class LocationInfogetLocationDataTest {

    @Test
    public void testGetLocationData() throws Exception {
        // Arrange
        LocationInfo locationInfo = new LocationInfo();
        Location expectedLocation = new Location();

        // Use reflection to set the private field
        Field locationDataField = LocationInfo.class.getDeclaredField("locationData");
        locationDataField.setAccessible(true);
        locationDataField.set(locationInfo, expectedLocation);

        // Act
        Location result = locationInfo.getLocationData();

        // Assert
        assertSame("Should return the same Location object", expectedLocation, result);
    }
}
