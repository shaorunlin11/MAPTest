package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

public class MediaFeedDatasetTypeTest {

    @Test
    public void testSetType() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        String expectedType = "testType";
        mediaFeedData.setType(expectedType);

        // Use reflection to verify the type field was set
        java.lang.reflect.Field typeField = MediaFeedData.class.getDeclaredField("type");
        typeField.setAccessible(true);
        String actualType = (String) typeField.get(mediaFeedData);

        assertEquals(expectedType, actualType);
    }
}
