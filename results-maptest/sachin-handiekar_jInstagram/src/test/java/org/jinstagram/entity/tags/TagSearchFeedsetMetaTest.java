package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.tags.TagSearchFeed;
import org.jinstagram.entity.tags.TagInfoData;

import java.util.List;
import java.util.ArrayList;

import java.lang.reflect.Field;


public class TagSearchFeedsetMetaTest {
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
    public void testSetMetaAssignsMetaToInstanceVariable() throws Exception {
        Meta expectedMeta = new Meta();
        tagSearchFeed.setMeta(expectedMeta);

        Field metaField = TagSearchFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        Meta actualMeta = (Meta) metaField.get(tagSearchFeed);

        Assert.assertEquals(expectedMeta, actualMeta);
    }

    @Test
    public void testSetMetaWithNullMeta() throws Exception {
        tagSearchFeed.setMeta(null);

        Field metaField = TagSearchFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        Meta actualMeta = (Meta) metaField.get(tagSearchFeed);

        Assert.assertNull(actualMeta);
    }
}
