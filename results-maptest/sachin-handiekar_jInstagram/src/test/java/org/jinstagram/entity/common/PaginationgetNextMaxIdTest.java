package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

import java.lang.reflect.Field;


public class PaginationgetNextMaxIdTest {
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
    public void testGetNextMaxId_ReturnsNullWhenNotSet() {
        Assert.assertNull(pagination.getNextMaxId());
    }

    @Test
    public void testGetNextMaxId_ReturnsExpectedValueWhenSet() throws Exception {
        String expectedNextMaxId = "12345";
        Field nextMaxIdField = Pagination.class.getDeclaredField("nextMaxId");
        nextMaxIdField.setAccessible(true);
        nextMaxIdField.set(pagination, expectedNextMaxId);

        Assert.assertEquals(expectedNextMaxId, pagination.getNextMaxId());
    }
}
