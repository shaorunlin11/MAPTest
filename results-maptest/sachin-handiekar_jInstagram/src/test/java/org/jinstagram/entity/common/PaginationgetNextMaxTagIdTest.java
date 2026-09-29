package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class PaginationgetNextMaxTagIdTest {
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
    public void testGetNextMaxTagId_returnsNullWhenNotInitialized() {
        Assert.assertNull(pagination.getNextMaxTagId());
    }

    @Test
    public void testGetNextMaxTagId_returnsExpectedValueWhenSet() throws Exception {
        String expected = "12345";
        Field field = Pagination.class.getDeclaredField("nextMaxTagId");
        field.setAccessible(true);
        field.set(pagination, expected);

        Assert.assertEquals(expected, pagination.getNextMaxTagId());
    }
}
