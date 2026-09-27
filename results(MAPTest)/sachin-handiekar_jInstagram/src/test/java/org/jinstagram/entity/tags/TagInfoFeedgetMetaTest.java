package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.entity.common.Meta;

import java.lang.reflect.Field;


public class TagInfoFeedgetMetaTest {

    @Test
    public void testGetMetaReturnsInitializedMeta() throws Exception {
        TagInfoFeed tagInfoFeed = new TagInfoFeed();
        Meta expectedMeta = new Meta();
        Field metaField = TagInfoFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(tagInfoFeed, expectedMeta);

        Meta result = tagInfoFeed.getMeta();

        Assert.assertEquals(expectedMeta, result);
    }

    @Test
    public void testGetMetaReturnsNullIfNotInitialized() throws Exception {
        TagInfoFeed tagInfoFeed = new TagInfoFeed();
        Field metaField = TagInfoFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(tagInfoFeed, null);

        Meta result = tagInfoFeed.getMeta();

        Assert.assertNull(result);
    }
}
