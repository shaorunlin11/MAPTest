package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UserFeedDatagetFullNameTest {

    @Test
    public void testGetFullName() throws Exception {
        UserFeedData userFeedData = new UserFeedData();
        String expectedFullName = "John Doe";
        Field fullNameField = UserFeedData.class.getDeclaredField("fullName");
        fullNameField.setAccessible(true);
        fullNameField.set(userFeedData, expectedFullName);

        String actualFullName = userFeedData.getFullName();
        assertEquals(expectedFullName, actualFullName);
    }
}
