package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.tags.TagInfoData;

import java.lang.reflect.Field;


public class TagSearchFeedtoStringTest {
    private TagSearchFeed tagSearchFeed;
    private Meta meta;
    private List<TagInfoData> tagList;

    @Before
    public void setUp() {
        tagSearchFeed = new TagSearchFeed();
        meta = new Meta();
        tagList = new ArrayList<TagInfoData>();
        tagList.add(new TagInfoData());
    }

    @After
    public void tearDown() {
        tagSearchFeed = null;
        meta = null;
        tagList = null;
    }

    @Test
    public void testToString() throws Exception {
        // Use reflection to set private fields
        Field metaField = TagSearchFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(tagSearchFeed, meta);

        Field tagListField = TagSearchFeed.class.getDeclaredField("tagList");
        tagListField.setAccessible(true);
        tagListField.set(tagSearchFeed, tagList);

        String result = tagSearchFeed.toString();

        Assert.assertTrue(result.contains("TagSearchFeed [meta="));
        Assert.assertTrue(result.contains(", tagList="));
    }
}
