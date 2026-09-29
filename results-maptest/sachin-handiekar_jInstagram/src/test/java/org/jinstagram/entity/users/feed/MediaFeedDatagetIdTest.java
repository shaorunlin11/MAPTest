package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

public class MediaFeedDatagetIdTest {

    @Test
    public void testGetId() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        String expectedId = "1234567890";

        // Use reflection to set the private id field
        java.lang.reflect.Field idField = MediaFeedData.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(mediaFeedData, expectedId);

        String actualId = mediaFeedData.getId();
        assertEquals(expectedId, actualId);
    }
}
