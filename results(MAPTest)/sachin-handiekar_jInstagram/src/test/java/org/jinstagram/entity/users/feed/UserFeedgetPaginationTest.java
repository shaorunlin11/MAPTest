package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.users.feed.UserFeed;
import org.jinstagram.entity.users.feed.UserFeedData;

import java.util.ArrayList;
import java.util.List;

public class UserFeedgetPaginationTest {
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
    public void testGetPaginationReturnsNullWhenNotSet() {
        Pagination result = userFeed.getPagination();
        Assert.assertNull("getPagination should return null when pagination is not set", result);
    }

    @Test
    public void testGetPaginationReturnsSetPagination() {
        Pagination expectedPagination = new Pagination();
        userFeed.setPagination(expectedPagination);

        Pagination result = userFeed.getPagination();
        Assert.assertEquals("getPagination should return the set pagination object", expectedPagination, result);
    }
}
