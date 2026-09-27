package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;

public class JsonFactoryBuilderbuildTest {

    @Test
    public void testBuild() throws Exception {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        JsonFactory factory = builder.build();
        Assert.assertNotNull(factory);
    }

    @Test
    public void testBuildWithBase() throws Exception {
        JsonFactory base = new JsonFactory();
        JsonFactoryBuilder builder = new JsonFactoryBuilder(base);
        JsonFactory factory = builder.build();
        Assert.assertNotNull(factory);
    }
}
