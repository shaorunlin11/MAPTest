package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;


public class PaginationgetMinTagIdTest {
    @Test
    public void testGetMinTagId() throws Exception {
        Pagination pagination = new Pagination();
        Assert.assertNull(pagination.getMinTagId());

        String expectedMinTagId = "12345";
        Field minTagIdField = Pagination.class.getDeclaredField("minTagId");
        minTagIdField.setAccessible(true);
        minTagIdField.set(pagination, expectedMinTagId);

        Assert.assertEquals(expectedMinTagId, pagination.getMinTagId());
    }
}
