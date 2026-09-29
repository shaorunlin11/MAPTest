package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class FromTagDatasetFullNameTest {
    private FromTagData fromTagData;

    @Before
    public void setUp() {
        fromTagData = new FromTagData();
    }

    @After
    public void tearDown() {
        fromTagData = null;
    }

    @Test
    public void testSetFullName() {
        String expectedFullName = "Test User";
        fromTagData.setFullName(expectedFullName);
        Assert.assertEquals(expectedFullName, fromTagData.getFullName());
    }
}
