package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UserFeedDatagetBioTest {

    @Test
    public void testGetBioReturnsBioField() throws Exception {
        UserFeedData userFeedData = new UserFeedData();
        String expectedBio = "This is a test bio.";
        Field bioField = UserFeedData.class.getDeclaredField("bio");
        bioField.setAccessible(true);
        bioField.set(userFeedData, expectedBio);

        String actualBio = userFeedData.getBio();
        assertEquals(expectedBio, actualBio);
    }

    @Test
    public void testGetBioReturnsNullWhenBioIsNotSet() throws Exception {
        UserFeedData userFeedData = new UserFeedData();
        Field bioField = UserFeedData.class.getDeclaredField("bio");
        bioField.setAccessible(true);
        bioField.set(userFeedData, null);

        String actualBio = userFeedData.getBio();
        assertNull(actualBio);
    }
}
