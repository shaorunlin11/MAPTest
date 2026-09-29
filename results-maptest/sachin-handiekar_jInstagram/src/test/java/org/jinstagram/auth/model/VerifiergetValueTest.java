package org.jinstagram.auth.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class VerifiergetValueTest {
    @Test
    public void testGetValue_ReturnsInitializedValue() throws Exception {
        String expectedValue = "test-verifier";
        Verifier verifier = new Verifier(expectedValue);
        String actualValue = verifier.getValue();
        assertEquals("getValue should return the initialized value", expectedValue, actualValue);
    }
}
