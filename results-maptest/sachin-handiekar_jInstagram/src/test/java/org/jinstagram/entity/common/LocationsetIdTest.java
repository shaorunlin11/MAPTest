package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class LocationsetIdTest {
    private Location location;

    @Before
    public void setUp() {
        location = new Location();
    }

    @Test
    public void testSetId() throws Exception {
        String expectedId = "12345";
        location.setId(expectedId);

        Field idField = Location.class.getDeclaredField("id");
        idField.setAccessible(true);
        String actualId = (String) idField.get(location);

        assertEquals(expectedId, actualId);
    }
}
