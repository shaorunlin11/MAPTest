package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.entity.common.Caption;

import java.lang.reflect.Field;

public class MediaFeedDatagetCaptionTest {

    @Test
    public void testGetCaption() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        Caption expectedCaption = new Caption();

        // Use reflection to set private field
        Field captionField = MediaFeedData.class.getDeclaredField("caption");
        captionField.setAccessible(true);
        captionField.set(mediaFeedData, expectedCaption);

        Caption actualCaption = mediaFeedData.getCaption();
        Assert.assertEquals(expectedCaption, actualCaption);
    }
}
