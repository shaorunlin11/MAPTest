package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

public class PaginationsetNextMaxIdTest {
    @Test
    public void testSetNextMaxId() throws Exception {
        Pagination pagination = new Pagination();
        String expectedNextMaxId = "test_next_max_id";

        pagination.setNextMaxId(expectedNextMaxId);

        java.lang.reflect.Field field = Pagination.class.getDeclaredField("nextMaxId");
        field.setAccessible(true);
        String actualNextMaxId = (String) field.get(pagination);

        Assert.assertEquals(expectedNextMaxId, actualNextMaxId);
    }
}
