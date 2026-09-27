package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.entity.common.Meta;

public class TagMediaFeedgetMetaTest {

    @Test
    public void testGetMeta() throws Exception {
        // Create a TagMediaFeed instance
        TagMediaFeed tagMediaFeed = new TagMediaFeed();

        // Create a Meta instance to set as the meta field
        Meta expectedMeta = new Meta();

        // Use reflection to set the private 'meta' field
        java.lang.reflect.Field metaField = TagMediaFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(tagMediaFeed, expectedMeta);

        // Call the getMeta method
        Meta actualMeta = tagMediaFeed.getMeta();

        // Assert that the returned Meta object matches the expected one
        Assert.assertEquals(expectedMeta, actualMeta);
    }
}
