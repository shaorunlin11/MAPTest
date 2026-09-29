package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class PaginationsetDepreciationWarningTest {
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
    public void testSetDepreciationWarningWithNonNullValue() {
        String testValue = "This is a depreciation warning";
        pagination.setDepreciationWarning(testValue);
        Assert.assertEquals(testValue, pagination.getDepreciationWarning());
    }

    @Test
    public void testSetDepreciationWarningWithNullValue() {
        pagination.setDepreciationWarning(null);
        Assert.assertNull(pagination.getDepreciationWarning());
    }
}
