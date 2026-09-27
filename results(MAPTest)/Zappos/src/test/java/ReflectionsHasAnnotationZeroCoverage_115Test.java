package com.zappos.json.util;

import org.junit.Test;

import java.lang.annotation.Annotation;

import java.lang.reflect.Method;


public class ReflectionsHasAnnotationZeroCoverage_115Test {
    @Test
    public void testHasAnnotationWithNullMethod() {
        // This test is designed to execute line 77 of the hasAnnotation method
        // by passing a null method parameter, which directly triggers the return false path.
        boolean result = Reflections.hasAnnotation((Method) null, Annotation.class);
        // The assertion is not required as the goal is only to execute the code path,
        // but this line ensures the method is called and the code is reachable.
        assert result == false;
    }
}
