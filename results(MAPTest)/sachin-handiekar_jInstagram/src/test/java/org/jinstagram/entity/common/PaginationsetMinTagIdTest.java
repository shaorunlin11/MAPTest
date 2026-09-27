package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

import java.lang.reflect.Field;

public class PaginationsetMinTagIdTest {
    private Pagination pagination;
    private Field minTagIdField;

    @Before
    public void setUp() throws Exception {
        pagination = new Pagination();
        minTagIdField = Pagination.class.getDeclaredField("minTagId");
        minTagIdField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        pagination = null;
        minTagIdField = null;
    }

    @Test
    public void testSetMinTagIdWithNonNullValue() throws Exception {
        String expectedMinTagId = "12345";
        pagination.setMinTagId(expectedMinTagId);
        String actualMinTagId = (String) minTagIdField.get(pagination);
        Assert.assertEquals(expectedMinTagId, actualMinTagId);
    }

    @Test
    public void testSetMinTagIdWithNullValue() throws Exception {
        String expectedMinTagId = null;
        pagination.setMinTagId(expectedMinTagId);
        String actualMinTagId = (String) minTagIdField.get(pagination);
        Assert.assertNull(actualMinTagId);
    }
}
