package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

public class MediaFeedDatasetLinkTest {

    @Test
    public void testSetLink() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        String testLink = "https://example.com/test";

        mediaFeedData.setLink(testLink);

        // Use reflection to verify the field value
        java.lang.reflect.Field linkField = MediaFeedData.class.getDeclaredField("link");
        linkField.setAccessible(true);
        String actualLink = (String) linkField.get(mediaFeedData);

        assertEquals("Link should be set correctly", testLink, actualLink);
    }
}
