package org.jinstagram.entity.media;

import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.junit.Test;
import org.junit.Assert;

public class MediaInfoFeedgetDataTest {
    @Test
    public void testGetData() throws Exception {
        MediaInfoFeed mediaInfoFeed = new MediaInfoFeed();
        MediaFeedData expectedData = new MediaFeedData();

        // Use reflection to set the private 'data' field
        java.lang.reflect.Field dataField = MediaInfoFeed.class.getDeclaredField("data");
        dataField.setAccessible(true);
        dataField.set(mediaInfoFeed, expectedData);

        MediaFeedData result = mediaInfoFeed.getData();
        Assert.assertEquals(expectedData, result);
    }
}
