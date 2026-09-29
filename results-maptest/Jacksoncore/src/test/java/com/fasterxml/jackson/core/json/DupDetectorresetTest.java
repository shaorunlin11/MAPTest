package com.fasterxml.jackson.core.json;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.HashSet;

import java.lang.reflect.Constructor;


public class DupDetectorresetTest {
    private DupDetector detector;

    @Before
    public void setUp() throws Exception {
        // Use reflection to access private constructor
        Constructor<DupDetector> constructor = DupDetector.class.getDeclaredConstructor(Object.class);
        constructor.setAccessible(true);
        detector = constructor.newInstance(new Object());
        detector._firstName = "test";
        detector._secondName = "test2";
        detector._seen = new HashSet<String>();
    }

    @Test
    public void testResetSetsFieldsToNull() throws Exception {
        detector.reset();
        assertNull(detector._firstName);
        assertNull(detector._secondName);
        assertNull(detector._seen);
    }
}
