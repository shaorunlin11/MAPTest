package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;


public class PaginationgetNextMinIdTest {
    @Test
    public void testGetNextMinId_returnsNullWhenNotSet() {
        Pagination pagination = new Pagination();
        Assert.assertNull(pagination.getNextMinId());
    }

    @Test
    public void testGetNextMinId_returnsSetValue() throws Exception {
        Pagination pagination = new Pagination();
        String expectedNextMinId = "test_min_id";
        Field nextMinIdField = Pagination.class.getDeclaredField("nextMinId");
        nextMinIdField.setAccessible(true);
        nextMinIdField.set(pagination, expectedNextMinId);
        Assert.assertEquals(expectedNextMinId, pagination.getNextMinId());
    }
}
