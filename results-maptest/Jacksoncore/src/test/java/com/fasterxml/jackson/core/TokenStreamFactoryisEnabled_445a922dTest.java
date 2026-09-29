package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Method;

public class TokenStreamFactoryisEnabled_445a922dTest {

    @Test
    public void testIsEnabled() {
        // This test confirms that the method exists in the abstract class
        try {
            Method method = TokenStreamFactory.class.getMethod("isEnabled", JsonParser.Feature.class);
            assertNotNull("isEnabled method should exist in TokenStreamFactory", method);
        } catch (NoSuchMethodException e) {
            fail("isEnabled method should exist in TokenStreamFactory: " + e.getMessage());
        }
    }
}
