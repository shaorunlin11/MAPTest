package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class PaginationsetNextCursorTest {
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
    public void testSetNextCursorWithNonNullValue() {
        String expectedNextCursor = "test_cursor";
        pagination.setNextCursor(expectedNextCursor);
        Assert.assertEquals(expectedNextCursor, pagination.getNextCursor());
    }

    @Test
    public void testSetNextCursorWithNullValue() {
        pagination.setNextCursor(null);
        Assert.assertNull(pagination.getNextCursor());
    }
}
