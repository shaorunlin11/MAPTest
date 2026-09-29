package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.users.feed.MediaFeedData;

import java.util.ArrayList;
import java.util.List;

public class TagMediaFeedgetPaginationTest {
    private TagMediaFeed tagMediaFeed;
    private Pagination expectedPagination;

    @Before
    public void setUp() {
        tagMediaFeed = new TagMediaFeed();
        expectedPagination = new Pagination();
        tagMediaFeed.setPagination(expectedPagination);
    }

    @After
    public void tearDown() {
        tagMediaFeed = null;
        expectedPagination = null;
    }

    @Test
    public void testGetPaginationReturnsExpectedValue() {
        Pagination result = tagMediaFeed.getPagination();
        Assert.assertEquals("Should return the same Pagination object that was set", expectedPagination, result);
    }
}
