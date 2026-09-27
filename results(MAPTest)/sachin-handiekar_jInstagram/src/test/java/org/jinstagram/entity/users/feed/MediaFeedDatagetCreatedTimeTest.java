package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

public class MediaFeedDatagetCreatedTimeTest {

    @Test
    public void testGetCreatedTime() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        String expectedCreatedTime = "1234567890";
        mediaFeedData.setCreatedTime(expectedCreatedTime);

        String actualCreatedTime = mediaFeedData.getCreatedTime();
        assertEquals(expectedCreatedTime, actualCreatedTime);
    }
}
