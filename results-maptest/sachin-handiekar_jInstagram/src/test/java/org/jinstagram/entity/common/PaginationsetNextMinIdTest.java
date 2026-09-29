package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class PaginationsetNextMinIdTest {
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
    public void testSetNextMinId() throws Exception {
        String expectedNextMinId = "testNextMinId";
        pagination.setNextMinId(expectedNextMinId);

        // Use reflection to verify the field value
        java.lang.reflect.Field field = Pagination.class.getDeclaredField("nextMinId");
        field.setAccessible(true);
        String actualNextMinId = (String) field.get(pagination);

        Assert.assertEquals(expectedNextMinId, actualNextMinId);
    }
}
