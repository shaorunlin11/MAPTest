package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

public class MediaFeedDatagetImageFilterTest {
    @Test
    public void testGetImageFilter_ReturnsDefaultValue() {
        MediaFeedData mediaFeedData = new MediaFeedData();
        assertNull("getImageFilter should return null when not initialized", mediaFeedData.getImageFilter());
    }

    @Test
    public void testGetImageFilter_ReturnsSetValues() {
        MediaFeedData mediaFeedData = new MediaFeedData();
        String expectedFilter = "vintage";
        mediaFeedData.setImageFilter(expectedFilter);
        assertEquals("getImageFilter should return the set value", expectedFilter, mediaFeedData.getImageFilter());
    }
}
