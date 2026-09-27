package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Ignore;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.lang.reflect.Field;

public class PaginationsetNextMaxTagIdTest {
    private Pagination pagination;
    private Field nextMaxTagIdField;

    @Before
    public void setUp() throws Exception {
        pagination = new Pagination();
        nextMaxTagIdField = Pagination.class.getDeclaredField("nextMaxTagId");
        nextMaxTagIdField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        pagination = null;
        nextMaxTagIdField = null;
    }

    @Test
    public void testSetNextMaxTagId() throws Exception {
        String expectedValue = "test_next_max_tag_id";

        pagination.setNextMaxTagId(expectedValue);

        String actualValue = (String) nextMaxTagIdField.get(pagination);
        Assert.assertEquals(expectedValue, actualValue);
    }
}
