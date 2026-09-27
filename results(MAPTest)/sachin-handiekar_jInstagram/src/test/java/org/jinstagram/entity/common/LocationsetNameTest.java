package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class LocationsetNameTest {
    private Location location;

    @Before
    public void setUp() {
        location = new Location();
    }

    @Test
    public void testSetNameSetsNameFieldCorrectly() throws Exception {
        String expectedName = "Test Location";
        location.setName(expectedName);

        Field nameField = Location.class.getDeclaredField("name");
        nameField.setAccessible(true);
        String actualName = (String) nameField.get(location);

        assertEquals(expectedName, actualName);
    }
}
