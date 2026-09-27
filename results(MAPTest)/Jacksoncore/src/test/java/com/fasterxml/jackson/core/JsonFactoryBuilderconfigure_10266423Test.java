package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.json.JsonReadFeature;

public class JsonFactoryBuilderconfigure_10266423Test {

    @Test
    public void testConfigure_EnableFeature() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        JsonReadFeature feature = JsonReadFeature.ALLOW_JAVA_COMMENTS;
        JsonFactoryBuilder result = builder.configure(feature, true);
        assertEquals(builder, result);
    }

    @Test
    public void testConfigure_DisableFeature() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        JsonReadFeature feature = JsonReadFeature.ALLOW_JAVA_COMMENTS;
        JsonFactoryBuilder result = builder.configure(feature, false);
        assertEquals(builder, result);
    }
}
