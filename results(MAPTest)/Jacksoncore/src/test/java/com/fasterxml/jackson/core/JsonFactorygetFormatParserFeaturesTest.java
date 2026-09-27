package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactorygetFormatParserFeaturesTest {
    @Test
    public void testGetFormatParserFeaturesReturnsZero() {
        JsonFactory factory = new JsonFactory();
        int result = factory.getFormatParserFeatures();
        assertEquals(0, result);
    }
}
