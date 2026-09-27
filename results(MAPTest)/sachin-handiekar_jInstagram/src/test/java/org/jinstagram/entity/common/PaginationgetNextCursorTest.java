package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class PaginationgetNextCursorTest {
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
    public void testGetNextCursorReturnsNullWhenNotSet() {
        assertNull("getNextCursor should return null when not set", pagination.getNextCursor());
    }

    @Test
    public void testGetNextCursorReturnsExpectedValue() throws Exception {
        String expectedNextCursor = "12345";
        Field nextCursorField = Pagination.class.getDeclaredField("nextCursor");
        nextCursorField.setAccessible(true);
        nextCursorField.set(pagination, expectedNextCursor);

        assertEquals("getNextCursor should return the correct value", expectedNextCursor, pagination.getNextCursor());
    }
}
