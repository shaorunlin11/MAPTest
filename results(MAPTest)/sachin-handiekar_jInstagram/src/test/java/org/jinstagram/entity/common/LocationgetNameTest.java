package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

public class LocationgetNameTest {

    @Test
    public void testGetName() throws Exception {
        // Create a Location instance
        Location location = new Location();

        // Set the name field using reflection to bypass private access
        java.lang.reflect.Field nameField = Location.class.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(location, "Test Location");

        // Call the getName method
        String result = location.getName();

        // Assert the result
        assertEquals("Test Location", result);
    }

    @Test
    public void testGetNameWhenNameIsNull() throws Exception {
        // Create a Location instance
        Location location = new Location();

        // Set the name field to null using reflection
        java.lang.reflect.Field nameField = Location.class.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(location, (String) null);

        // Call the getName method
        String result = location.getName();

        // Assert the result
        assertNull(result);
    }
}
