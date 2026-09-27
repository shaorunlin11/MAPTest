package org.jinstagram.entity.media;

import org.junit.Test;
import org.junit.Assert;

import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.entity.common.Meta;

import java.lang.reflect.Field;


public class MediaInfoFeedsetDataTest {

    @Test
    public void testSetData() throws Exception {
        MediaInfoFeed mediaInfoFeed = new MediaInfoFeed();
        MediaFeedData testData = new MediaFeedData();

        mediaInfoFeed.setData(testData);

        // Verify that the data was set correctly
        Field dataField = mediaInfoFeed.getClass().getDeclaredField("data");
        dataField.setAccessible(true);
        Assert.assertEquals(testData, dataField.get(mediaInfoFeed));
    }
}
