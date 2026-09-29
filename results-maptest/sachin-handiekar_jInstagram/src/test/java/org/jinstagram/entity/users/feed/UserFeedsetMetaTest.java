package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.Pagination;
import java.util.List;
import java.util.ArrayList;

public class UserFeedsetMetaTest {
    private UserFeed userFeed;

    @Before
    public void setUp() {
        userFeed = new UserFeed();
    }

    @After
    public void tearDown() {
        userFeed = null;
    }

    @Test
    public void testSetMeta() throws Exception {
        Meta expectedMeta = new Meta();
        userFeed.setMeta(expectedMeta);

        // Use reflection to verify the field was set
        java.lang.reflect.Field metaField = UserFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        Meta actualMeta = (Meta) metaField.get(userFeed);

        Assert.assertEquals(expectedMeta, actualMeta);
    }
}
