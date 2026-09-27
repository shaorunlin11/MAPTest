package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.annotations.SerializedName;

import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.users.feed.MediaFeedData;

import java.lang.reflect.Field;


public class MediaFeedgetPaginationTest {
    private MediaFeed mediaFeed;

    @Before
    public void setUp() {
        mediaFeed = new MediaFeed();
    }

    @After
    public void tearDown() {
        mediaFeed = null;
    }

    @Test
    public void testGetPaginationReturnsInitializedValue() throws Exception {
        Pagination expectedPagination = new Pagination();
        Field paginationField = MediaFeed.class.getDeclaredField("pagination");
        paginationField.setAccessible(true);
        paginationField.set(mediaFeed, expectedPagination);

        Pagination result = mediaFeed.getPagination();
        Assert.assertEquals(expectedPagination, result);
    }
}
