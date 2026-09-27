package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactorygetFormatReadFeatureTypeTest {
    @Test
    public void testGetFormatReadFeatureTypeReturnsNullByDefault() {
        JsonFactory factory = new JsonFactory();
        Class<? extends FormatFeature> result = factory.getFormatReadFeatureType();
        assertNull("getFormatReadFeatureType should return null by default", result);
    }
}
