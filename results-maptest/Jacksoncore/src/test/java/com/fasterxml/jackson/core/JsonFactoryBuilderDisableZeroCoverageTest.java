package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.json.JsonReadFeature;


public class JsonFactoryBuilderDisableZeroCoverageTest {
    @Test
    public void testDisableMethodWithNonNullFeature() {
        JsonFactoryBuilder builder = new JsonFactoryBuilder();
        JsonReadFeature feature = JsonReadFeature.ALLOW_JAVA_COMMENTS;

        // Ensure the feature is not null and mappedFeature() returns a non-null value
        assertNotNull(feature);
        assertNotNull(feature.mappedFeature());

        // Call the method under test
        builder.disable(feature);
    }
}
