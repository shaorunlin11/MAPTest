package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

public class FromTagDatagetFullNameTest {
    @Test
    public void testGetFullName() throws Exception {
        FromTagData fromTagData = new FromTagData();
        String expectedFullName = "John Doe";

        // Use reflection to set the private field
        java.lang.reflect.Field fullNameField = FromTagData.class.getDeclaredField("fullName");
        fullNameField.setAccessible(true);
        fullNameField.set(fromTagData, expectedFullName);

        String actualFullName = fromTagData.getFullName();
        Assert.assertEquals(expectedFullName, actualFullName);
    }
}
