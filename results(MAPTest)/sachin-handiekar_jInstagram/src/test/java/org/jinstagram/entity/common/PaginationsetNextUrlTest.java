package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Field;

@RunWith(JUnit4.class)
public class PaginationsetNextUrlTest {
    private Pagination pagination;
    private Field nextUrlField;

    @Before
    public void setUp() throws Exception {
        pagination = new Pagination();
        nextUrlField = Pagination.class.getDeclaredField("nextUrl");
        nextUrlField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        pagination = null;
        nextUrlField = null;
    }

    @Test
    public void testSetNextUrlSetsValueCorrectly() throws Exception {
        String testUrl = "https://example.com/next";
        pagination.setNextUrl(testUrl);

        Assert.assertEquals(testUrl, nextUrlField.get(pagination));
    }

    @Test
    public void testSetNextUrlWithNullValue() throws Exception {
        pagination.setNextUrl(null);

        Assert.assertNull(nextUrlField.get(pagination));
    }
}
