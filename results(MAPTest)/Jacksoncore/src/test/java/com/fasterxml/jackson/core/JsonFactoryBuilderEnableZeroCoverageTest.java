package com.fasterxml.jackson.core;

import org.junit.Test;

import com.fasterxml.jackson.core.json.JsonReadFeature;


public class JsonFactoryBuilderEnableZeroCoverageTest {
    @Test
    public void testEnableWithNonEmptyOther() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        JsonReadFeature feature1 = JsonReadFeature.ALLOW_YAML_COMMENTS;
        JsonReadFeature feature2 = JsonReadFeature.ALLOW_JAVA_COMMENTS;

        builder.enable(feature1, feature2);
    }
}
