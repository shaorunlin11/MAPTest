package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;

import org.jinstagram.entity.common.Location;

public class MediaFeedDatasetLocationTest {

    @Test
    public void testSetLocation() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        Location expectedLocation = new Location();

        mediaFeedData.setLocation(expectedLocation);

        // Use reflection to verify the private 'location' field
        java.lang.reflect.Field locationField = MediaFeedData.class.getDeclaredField("location");
        locationField.setAccessible(true);
        Location actualLocation = (Location) locationField.get(mediaFeedData);

        Assert.assertEquals(expectedLocation, actualLocation);
    }
}
