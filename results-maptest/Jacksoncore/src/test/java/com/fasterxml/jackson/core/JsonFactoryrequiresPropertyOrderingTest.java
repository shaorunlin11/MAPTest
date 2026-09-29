package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactoryrequiresPropertyOrderingTest {
    @Test
    public void testRequiresPropertyOrderingReturnsFalse() {
        JsonFactory factory = new JsonFactory();
        assertFalse(factory.requiresPropertyOrdering());
    }
}
