package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.users.feed.UserFeedData;

public class UserFeedtoStringTest {

    private UserFeed userFeed;
    private Meta meta;
    private Pagination pagination;
    private List<UserFeedData> userList;

    @Before
    public void setUp() {
        userFeed = new UserFeed();
        meta = new Meta();
        pagination = new Pagination();
        userList = new ArrayList<UserFeedData>();
    }

    @After
    public void tearDown() {
        userFeed = null;
        meta = null;
        pagination = null;
        userList = null;
    }

    @Test
    public void testToString() throws Exception {
        // Use reflection to set private fields
        java.lang.reflect.Field metaField = UserFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(userFeed, meta);

        java.lang.reflect.Field paginationField = UserFeed.class.getDeclaredField("pagination");
        paginationField.setAccessible(true);
        paginationField.set(userFeed, pagination);

        java.lang.reflect.Field userListField = UserFeed.class.getDeclaredField("userList");
        userListField.setAccessible(true);
        userListField.set(userFeed, userList);

        String result = userFeed.toString();

        Assert.assertTrue(result.contains("UserFeed [meta="));
        Assert.assertTrue(result.contains(", pagination="));
        Assert.assertTrue(result.contains(", userList="));
    }
}
