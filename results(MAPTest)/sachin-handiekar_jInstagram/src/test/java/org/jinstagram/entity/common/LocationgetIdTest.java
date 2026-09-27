package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class LocationgetIdTest {
    @Test
    public void testGetId() throws Exception {
        Location location = new Location();
        String expectedId = "12345";
        Field idField = Location.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(location, expectedId);

        String actualId = location.getId();
        assertEquals(expectedId, actualId);
    }
}
