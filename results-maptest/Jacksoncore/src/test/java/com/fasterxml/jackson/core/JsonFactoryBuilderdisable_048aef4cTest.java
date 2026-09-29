package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import com.fasterxml.jackson.core.json.JsonReadFeature;

public class JsonFactoryBuilderdisable_048aef4cTest {
    private JsonFactoryBuilder builder;

    @Before
    public void setUp() {
        builder = new JsonFactoryBuilder();
    }

    @After
    public void tearDown() {
        builder = null;
    }

    @Test
    public void testDisableSingleFeature() {
        JsonReadFeature feature = JsonReadFeature.ALLOW_JAVA_COMMENTS;
        JsonFactoryBuilder result = builder.disable(feature);
        Assert.assertEquals(builder, result);
    }

    @Test
    public void testDisableMultipleFeatures() {
        JsonReadFeature first = JsonReadFeature.ALLOW_JAVA_COMMENTS;
        JsonReadFeature second = JsonReadFeature.ALLOW_YAML_COMMENTS;
        JsonFactoryBuilder result = builder.disable(first, second);
        Assert.assertEquals(builder, result);
    }

    @Test
    public void testDisableWithEmptyOtherArray() {
        JsonReadFeature first = JsonReadFeature.ALLOW_JAVA_COMMENTS;
        JsonFactoryBuilder result = builder.disable(first);
        Assert.assertEquals(builder, result);
    }

    @Test
    public void testDisableWithMultipleOtherFeatures() {
        JsonReadFeature first = JsonReadFeature.ALLOW_JAVA_COMMENTS;
        JsonReadFeature second = JsonReadFeature.ALLOW_YAML_COMMENTS;
        JsonReadFeature third = JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES;
        JsonFactoryBuilder result = builder.disable(first, second, third);
        Assert.assertEquals(builder, result);
    }
}
