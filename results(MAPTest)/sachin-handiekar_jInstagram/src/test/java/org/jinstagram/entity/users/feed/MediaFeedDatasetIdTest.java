package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

public class MediaFeedDatasetIdTest {
    @Test
    public void testSetId() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        String expectedId = "testId123";

        mediaFeedData.setId(expectedId);

        // Use reflection to verify the id field was set correctly
        java.lang.reflect.Field idField = MediaFeedData.class.getDeclaredField("id");
        idField.setAccessible(true);
        String actualId = (String) idField.get(mediaFeedData);

        assertEquals(expectedId, actualId);
    }
}
