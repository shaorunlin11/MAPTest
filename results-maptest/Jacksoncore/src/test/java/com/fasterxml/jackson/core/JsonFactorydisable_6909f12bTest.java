package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class JsonFactorydisable_6909f12bTest {

    @Test
    public void testDisableGeneratorFeature() throws Exception {
        // Create a JsonFactory instance
        JsonFactory factory = new JsonFactory();

        // Get the _generatorFeatures field
        Field generatorFeaturesField = JsonFactory.class.getDeclaredField("_generatorFeatures");
        generatorFeaturesField.setAccessible(true);

        // Get the initial value of _generatorFeatures
        Object initialFeatures = generatorFeaturesField.get(factory);

        // Get the feature to disable
        JsonGenerator.Feature featureToDisable = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS;

        // Call disable method
        JsonFactory result = factory.disable(featureToDisable);

        // Verify that the same instance is returned
        assertTrue(result == factory);

        // Get the updated value of _generatorFeatures
        Object updatedFeatures = generatorFeaturesField.get(factory);

        // Verify that the feature bit is cleared
        int mask = featureToDisable.getMask();
        assertFalse(((Integer)updatedFeatures & mask) != 0);
    }
}
