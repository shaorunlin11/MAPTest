package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class JsonFactorygetParserFeaturesTest {
    @Test
    public void testGetParserFeatures() throws Exception {
        JsonFactory factory = new JsonFactory();
        int expected = JsonParser.Feature.collectDefaults();
        assertEquals("Default parser features should match collected defaults", expected, factory.getParserFeatures());

        // Modify the parser features and check if the change is reflected
        Field parserFeaturesField = JsonFactory.class.getDeclaredField("_parserFeatures");
        parserFeaturesField.setAccessible(true);
        parserFeaturesField.setInt(factory, 0x12345678);

        int actual = factory.getParserFeatures();
        assertEquals("Modified parser features should be returned", 0x12345678, actual);
    }
}
