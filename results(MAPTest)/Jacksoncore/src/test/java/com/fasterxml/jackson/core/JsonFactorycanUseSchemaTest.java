package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactorycanUseSchemaTest {

    @Test
    public void testCanUseSchemaWithNullSchema() {
        JsonFactory factory = new JsonFactory();
        assertFalse(factory.canUseSchema(null));
    }

    @Test
    public void testCanUseSchemaWithNonNullSchemaAndMatchingFormatName() throws Exception {
        JsonFactory factory = new JsonFactory();
        FormatSchema schema = new FormatSchema() {
            @Override
            public String getSchemaType() {
                return "JSON";
            }
        };
        assertTrue(factory.canUseSchema(schema));
    }

    @Test
    public void testCanUseSchemaWithNonNullSchemaAndNonMatchingFormatName() throws Exception {
        JsonFactory factory = new JsonFactory();
        FormatSchema schema = new FormatSchema() {
            @Override
            public String getSchemaType() {
                return "XML";
            }
        };
        assertFalse(factory.canUseSchema(schema));
    }

    @Test
    public void testCanUseSchemaWithNonNullSchemaAndNullFormatName() throws Exception {
        JsonFactory factory = new JsonFactory() {
            @Override
            public String getFormatName() {
                return null;
            }
        };
        FormatSchema schema = new FormatSchema() {
            @Override
            public String getSchemaType() {
                return "JSON";
            }
        };
        assertFalse(factory.canUseSchema(schema));
    }
}
