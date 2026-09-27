package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.json.JsonReadFeature;

public class JsonFactoryBuilderenable_182d4f10Test {

    @Test
    public void testEnableWithJsonReadFeature() throws Exception {
        // Arrange
        JsonFactoryBuilder builder = new JsonFactoryBuilder();

        // Act
        JsonFactoryBuilder result = builder.enable(JsonReadFeature.ALLOW_YAML_COMMENTS);

        // Assert
        assertNotNull(result);
        assertSame(builder, result);
    }
}
