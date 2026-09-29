package org.jinstagram.entity.likes;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.User;

import java.lang.reflect.Field;


public class LikesFeedsetMetaTest {
    private LikesFeed likesFeed;

    @Before
    public void setUp() {
        likesFeed = new LikesFeed();
    }

    @Test
    public void testSetMetaAssignsMetaToField() throws Exception {
        Meta mockMeta = new Meta();
        likesFeed.setMeta(mockMeta);

        Field metaField = LikesFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        Meta result = (Meta) metaField.get(likesFeed);

        Assert.assertEquals(mockMeta, result);
    }
}
