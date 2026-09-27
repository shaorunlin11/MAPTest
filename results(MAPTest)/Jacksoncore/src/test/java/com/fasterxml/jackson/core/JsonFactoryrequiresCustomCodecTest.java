package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonFactoryrequiresCustomCodecTest {
    @Test
    public void testRequiresCustomCodecReturnsFalse() {
        JsonFactory factory = new JsonFactory();
        assertFalse(factory.requiresCustomCodec());
    }
}
