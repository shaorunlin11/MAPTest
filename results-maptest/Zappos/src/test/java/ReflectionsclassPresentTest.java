package com.zappos.json.util;

import org.junit.Test;
import org.junit.Assert;

public class ReflectionsclassPresentTest {
    @Test
    public void testClassPresentWithExistingClass() {
        boolean result = Reflections.classPresent("java.lang.String");
        Assert.assertTrue("Expected class to be present", result);
    }

    @Test
    public void testClassPresentWithNonExistingClass() {
        boolean result = Reflections.classPresent("com.zappos.json.util.NonExistentClass");
        Assert.assertFalse("Expected class to not be present", result);
    }

    @Test
    public void testClassPresentWithNullClassName() {
        boolean result = Reflections.classPresent("invalid.class.name.which.does.not.exist");
        Assert.assertFalse("Expected class to not be present", result);
    }
}
