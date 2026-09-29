package com.zappos.json;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertNotNull;

public class ZapposJsonGetInstanceZeroCoverageTest {
    @Test
    public void testGetInstanceWithValidName() {
        // Arrange
        String name = "testName";
        Map<String, ZapposJson> INSTANCES = new ConcurrentHashMap<>();
        INSTANCES.put(name, new ZapposJson());

        // Act
        ZapposJson result = ZapposJson.getInstance(name);

        // Assert
        assertNotNull(result);
    }
}
