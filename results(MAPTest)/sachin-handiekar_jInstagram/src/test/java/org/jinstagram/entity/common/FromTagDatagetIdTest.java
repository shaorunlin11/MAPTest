package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.lang.reflect.Field;

public class FromTagDatagetIdTest {
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
    public void testGetId_ReturnsInitializedValue() throws Exception {
        String expectedId = "1234567890";
        Field idField = FromTagData.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(fromTagData, expectedId);
        Assert.assertEquals(expectedId, fromTagData.getId());
    }

    @Test
    public void testGetId_ReturnsNullIfNotInitialized() {
        Assert.assertNull(fromTagData.getId());
    }
}
