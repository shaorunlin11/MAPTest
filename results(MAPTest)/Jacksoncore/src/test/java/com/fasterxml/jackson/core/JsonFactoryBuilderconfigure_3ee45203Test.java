package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.json.JsonWriteFeature;

public class JsonFactoryBuilderconfigure_3ee45203Test {

    @Test
    public void testConfigureEnable() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        JsonWriteFeature feature = JsonWriteFeature.QUOTE_FIELD_NAMES;
        JsonFactoryBuilder result = builder.configure(feature, true);
        assertSame(builder, result);
    }

    @Test
    public void testConfigureDisable() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        JsonWriteFeature feature = JsonWriteFeature.QUOTE_FIELD_NAMES;
        JsonFactoryBuilder result = builder.configure(feature, false);
        assertSame(builder, result);
    }
}
