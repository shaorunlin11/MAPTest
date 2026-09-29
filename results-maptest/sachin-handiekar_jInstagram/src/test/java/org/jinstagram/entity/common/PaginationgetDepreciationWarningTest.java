package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Assert;
import java.lang.reflect.Field;

public class PaginationgetDepreciationWarningTest {
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
    public void testGetDepreciationWarningReturnsNullWhenNotSet() {
        Assert.assertNull(pagination.getDepreciationWarning());
    }

    @Test
    public void testGetDepreciationWarningReturnsSetStringValue() throws Exception {
        String expectedWarning = "This endpoint is deprecated";
        Field depreciationWarningField = Pagination.class.getDeclaredField("depreciationWarning");
        depreciationWarningField.setAccessible(true);
        depreciationWarningField.set(pagination, expectedWarning);
        Assert.assertEquals(expectedWarning, pagination.getDepreciationWarning());
    }
}
