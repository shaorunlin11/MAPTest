package com.zappos.json.util;

import org.junit.Test;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ReflectionsHasAnnotationZeroCoverageTest {
    @Test
    public void testHasAnnotationWithNonNullField() throws Exception {
        // Create a test class with a field annotated with a sample annotation
        class TestClass {
            @SampleAnnotation
            public String testField;
        }

        // Get the field
        Field field = TestClass.class.getDeclaredField("testField");

        // Check if the field has the annotation
        boolean result = Reflections.hasAnnotation(field, SampleAnnotation.class);
    }

    // Sample annotation for testing
    @interface SampleAnnotation {}

@Test
    public void testHasAnnotationWithNullField() throws Exception {
        // Create a test class with a field annotated with a sample annotation
        class TestClass {
            @SampleAnnotation
            public String testField;
        }

        // Get the field
        Field field = TestClass.class.getDeclaredField("testField");

        // Set the field to null to trigger the target line
        field = null;

        // Check if the field has the annotation
        boolean result = Reflections.hasAnnotation(field, SampleAnnotation.class);
    }
}
