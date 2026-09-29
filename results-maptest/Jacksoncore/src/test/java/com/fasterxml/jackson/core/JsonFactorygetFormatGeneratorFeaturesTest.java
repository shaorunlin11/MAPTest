package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactorygetFormatGeneratorFeaturesTest {
    @Test
    public void testGetFormatGeneratorFeaturesReturnsZero() throws Exception {
        JsonFactory factory = new JsonFactory();
        int result = factory.getFormatGeneratorFeatures();
        assertEquals(0, result);
    }
}
