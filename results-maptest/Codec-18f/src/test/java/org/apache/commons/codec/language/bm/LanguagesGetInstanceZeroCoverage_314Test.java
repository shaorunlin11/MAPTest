package org.apache.commons.codec.language.bm;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class LanguagesGetInstanceZeroCoverage_314Test {
    @Test
    public void testGetInstanceWithNonExistentResource() {
        final String nonExistentResource = "nonexistent-resource.txt";

        try {
            Languages.getInstance(nonExistentResource);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Unable to resolve required resource: " + nonExistentResource, e.getMessage());
        }
    }
}
