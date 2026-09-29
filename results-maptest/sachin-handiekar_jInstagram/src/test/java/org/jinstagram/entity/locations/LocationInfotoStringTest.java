package org.jinstagram.entity.locations;

import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Location;
import com.google.gson.annotations.SerializedName;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class LocationInfotoStringTest {

    @Test
    public void testToString() throws Exception {
        // Create a LocationInfo instance with a mock Location object
        LocationInfo locationInfo = new LocationInfo();

        // Use reflection to set the locationData field
        Field locationDataField = LocationInfo.class.getDeclaredField("locationData");
        locationDataField.setAccessible(true);
        Location mockLocation = new Location();
        locationDataField.set(locationInfo, mockLocation);

        // Call the toString method
        String result = locationInfo.toString();

        // Assert that the result matches the expected format
        assertTrue(result.startsWith("LocationInfo [locationData="));
        assertTrue(result.endsWith("]"));
    }
}
