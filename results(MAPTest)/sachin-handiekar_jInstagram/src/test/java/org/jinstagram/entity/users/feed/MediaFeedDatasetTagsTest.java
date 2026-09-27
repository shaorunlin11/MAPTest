package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class MediaFeedDatasetTagsTest {
    private MediaFeedData mediaFeedData;

    @Before
    public void setUp() {
        mediaFeedData = new MediaFeedData();
    }

    @After
    public void tearDown() {
        mediaFeedData = null;
    }

    @Test
    public void testSetTags() {
        List<String> expectedTags = new ArrayList<String>();
        expectedTags.add("tag1");
        expectedTags.add("tag2");

        mediaFeedData.setTags(expectedTags);

        Assert.assertEquals(expectedTags, mediaFeedData.getTags());
    }
}
