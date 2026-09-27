package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactorygetFormatWriteFeatureTypeTest {
    @Test
    public void testGetFormatWriteFeatureTypeReturnsNull() {
        JsonFactory factory = new JsonFactory();
        Class<? extends FormatFeature> result = factory.getFormatWriteFeatureType();
        assertNull("getFormatWriteFeatureType should return null by default", result);
    }
}
