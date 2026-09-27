package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

public class LocationtoStringTest {

    @Test
    public void testToString() throws Exception {
        Location location = new Location();
        location.setId("12345");
        location.setName("Test Location");
        location.setLatitude(40.7128);
        location.setLongitude(-74.0060);

        String result = location.toString();

        assertEquals("Location [id=12345, name=Test Location, latitude=40.7128, longitude=-74.006]", result);
    }
}
