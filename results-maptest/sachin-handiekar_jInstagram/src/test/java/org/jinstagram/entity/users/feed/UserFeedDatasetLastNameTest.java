package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UserFeedDatasetLastNameTest {
    private UserFeedData userFeedData;

    @Before
    public void setUp() {
        userFeedData = new UserFeedData();
    }

    @Test
    public void testSetLastNameAssignsValueToLastNameField() throws Exception {
        String expectedLastName = "Doe";
        userFeedData.setLastName(expectedLastName);

        Field lastNameField = UserFeedData.class.getDeclaredField("lastName");
        lastNameField.setAccessible(true);
        String actualLastName = (String) lastNameField.get(userFeedData);

        assertEquals(expectedLastName, actualLastName);
    }
}
