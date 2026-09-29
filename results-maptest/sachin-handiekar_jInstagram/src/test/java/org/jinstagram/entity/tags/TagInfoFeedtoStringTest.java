package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Assert;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.tags.TagInfoData;

public class TagInfoFeedtoStringTest {

    @Test
    public void testToString() throws Exception {
        // Create mock objects
        Meta meta = new Meta();
        TagInfoData tagInfo = new TagInfoData();

        // Create TagInfoFeed instance using reflection to set private fields
        TagInfoFeed feed = new TagInfoFeed();

        // Use reflection to set the 'meta' field
        java.lang.reflect.Field metaField = TagInfoFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(feed, meta);

        // Use reflection to set the 'tagInfo' field
        java.lang.reflect.Field tagInfoField = TagInfoFeed.class.getDeclaredField("tagInfo");
        tagInfoField.setAccessible(true);
        tagInfoField.set(feed, tagInfo);

        // Call toString method
        String result = feed.toString();

        // Assert the expected format
        Assert.assertTrue(result.startsWith("TagInfoFeed [meta="));
        Assert.assertTrue(result.contains(", tagInfo="));
        Assert.assertTrue(result.endsWith("]"));
    }
}
