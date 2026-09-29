package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

public class MediaFeedDatagetLinkTest {

    @Test
    public void testGetLink() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        String expectedLink = "https://example.com/media";

        // Use reflection to set private field
        java.lang.reflect.Field linkField = MediaFeedData.class.getDeclaredField("link");
        linkField.setAccessible(true);
        linkField.set(mediaFeedData, expectedLink);

        String actualLink = mediaFeedData.getLink();
        assertEquals(expectedLink, actualLink);
    }
}
