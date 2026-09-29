package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.tags.TagInfoData;
import java.util.List;
import java.util.ArrayList;

public class TagSearchFeedgetMetaTest {
    private TagSearchFeed tagSearchFeed;

    @Before
    public void setUp() {
        tagSearchFeed = new TagSearchFeed();
    }

    @After
    public void tearDown() {
        tagSearchFeed = null;
    }

    @Test
    public void testGetMeta_ReturnsMetaObject() {
        Meta expectedMeta = new Meta();
        // Use reflection to set the private field
        try {
            java.lang.reflect.Field metaField = TagSearchFeed.class.getDeclaredField("meta");
            metaField.setAccessible(true);
            metaField.set(tagSearchFeed, expectedMeta);
        } catch (Exception e) {
            Assert.fail("Failed to set private field: " + e.getMessage());
        }

        Meta result = tagSearchFeed.getMeta();

        Assert.assertEquals(expectedMeta, result);
    }

    @Test
    public void testGetMeta_WhenMetaIsNull_ReturnsNull() {
        // Use reflection to set the private field
        try {
            java.lang.reflect.Field metaField = TagSearchFeed.class.getDeclaredField("meta");
            metaField.setAccessible(true);
            metaField.set(tagSearchFeed, null);
        } catch (Exception e) {
            Assert.fail("Failed to set private field: " + e.getMessage());
        }

        Meta result = tagSearchFeed.getMeta();

        Assert.assertNull(result);
    }
}
