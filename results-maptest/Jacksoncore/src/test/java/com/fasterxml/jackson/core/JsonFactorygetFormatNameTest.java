package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactorygetFormatNameTest {
    @Test
    public void testGetFormatNameWhenClassIsJsonFactory() {
        JsonFactory factory = new JsonFactory();
        assertEquals("JSON", factory.getFormatName());
    }

    @Test
    public void testGetFormatNameWhenClassIsNotJsonFactory() {
        // Create a subclass of JsonFactory
        class TestJsonFactory extends JsonFactory {
            public TestJsonFactory() {
                super((ObjectCodec) null);
            }
        }

        TestJsonFactory testFactory = new TestJsonFactory();
        assertNull(testFactory.getFormatName());
    }
}
