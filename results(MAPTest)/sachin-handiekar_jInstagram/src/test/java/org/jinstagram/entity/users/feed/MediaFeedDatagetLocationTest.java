package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;

public class MediaFeedDatagetLocationTest {
    @Test
    public void testGetLocation() {
        MediaFeedData mediaFeedData = new MediaFeedData();
        org.jinstagram.entity.common.Location expectedLocation = new org.jinstagram.entity.common.Location();
        mediaFeedData.setLocation(expectedLocation);

        org.jinstagram.entity.common.Location result = mediaFeedData.getLocation();
        Assert.assertEquals(expectedLocation, result);
    }

    @Test
    public void testGetLocationWhenNull() {
        MediaFeedData mediaFeedData = new MediaFeedData();
        org.jinstagram.entity.common.Location result = mediaFeedData.getLocation();
        Assert.assertNull(result);
    }
}
