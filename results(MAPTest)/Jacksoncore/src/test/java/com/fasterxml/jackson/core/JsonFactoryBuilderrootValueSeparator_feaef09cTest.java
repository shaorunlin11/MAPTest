package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;

public class JsonFactoryBuilderrootValueSeparator_feaef09cTest {
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
    public void testRootValueSeparatorReturnsInitializedValue() {
        SerializableString expected = new SerializedString(";");
        // Use the setter method if available, otherwise use reflection
        try {
            java.lang.reflect.Field field = JsonFactoryBuilder.class.getDeclaredField("_rootValueSeparator");
            field.setAccessible(true);
            field.set(builder, expected);
        } catch (Exception e) {
            Assert.fail("Failed to set _rootValueSeparator field: " + e.getMessage());
        }
        SerializableString result = builder.rootValueSeparator();
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testRootValueSeparatorDefaultValue() {
        SerializableString defaultValue = JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR;
        SerializableString result = builder.rootValueSeparator();
        Assert.assertEquals(defaultValue, result);
    }
}
