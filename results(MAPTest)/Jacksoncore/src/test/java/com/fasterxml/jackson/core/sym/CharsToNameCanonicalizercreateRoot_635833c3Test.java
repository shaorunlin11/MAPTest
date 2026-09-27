package com.fasterxml.jackson.core.sym;

import org.junit.Test;
import static org.junit.Assert.*;

public class CharsToNameCanonicalizercreateRoot_635833c3Test {

    @Test
    public void testCreateRoot() {
        CharsToNameCanonicalizer result = CharsToNameCanonicalizer.createRoot();
        assertNotNull("createRoot should return a non-null instance", result);
    }
}
