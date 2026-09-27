package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class MediaFeedDatagetTypeTest {

    @Test
    public void testGetType_ReturnsInitializedValue() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        String expectedType = "image";
        Field typeField = MediaFeedData.class.getDeclaredField("type");
        typeField.setAccessible(true);
        typeField.set(mediaFeedData, expectedType);

        String result = mediaFeedData.getType();
        assertEquals(expectedType, result);
    }

    @Test
    public void testGetType_ReturnsNullIfNotSet() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        Field typeField = MediaFeedData.class.getDeclaredField("type");
        typeField.setAccessible(true);
        typeField.set(mediaFeedData, null);

        String result = mediaFeedData.getType();
        assertNull(result);
    }
}
