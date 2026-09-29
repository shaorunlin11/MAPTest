package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonpCharacterEscapesinstanceTest {
    @Test
    public void testInstanceReturnsSingleton() {
        JsonpCharacterEscapes instance1 = JsonpCharacterEscapes.instance();
        JsonpCharacterEscapes instance2 = JsonpCharacterEscapes.instance();
        assertSame("Should return the same singleton instance", instance1, instance2);
    }
}
