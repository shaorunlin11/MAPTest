package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

import java.lang.reflect.Field;


public class PaginationgetNextUrlTest {
    private Pagination pagination;

    @Before
    public void setUp() {
        pagination = new Pagination();
    }

    @After
    public void tearDown() {
        pagination = null;
    }

    @Test
    public void testGetNextUrl_returnsNullWhenNotSet() {
        Assert.assertNull(pagination.getNextUrl());
    }

    @Test
    public void testGetNextUrl_returnsSetNextUrl() throws Exception {
        String expectedNextUrl = "https://example.com/next";
        Field nextUrlField = Pagination.class.getDeclaredField("nextUrl");
        nextUrlField.setAccessible(true);
        nextUrlField.set(pagination, expectedNextUrl);

        Assert.assertEquals(expectedNextUrl, pagination.getNextUrl());
    }
}
