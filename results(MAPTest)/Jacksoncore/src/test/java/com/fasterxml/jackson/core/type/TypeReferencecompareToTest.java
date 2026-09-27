package com.fasterxml.jackson.core.type;

import org.junit.Test;
import static org.junit.Assert.*;

public class TypeReferencecompareToTest {
    @Test
    public void testCompareToAlwaysReturnsZero() {
        TypeReference<String> reference1 = new TypeReference<String>() {};
        TypeReference<String> reference2 = new TypeReference<String>() {};

        assertEquals(0, reference1.compareTo(reference2));
    }
}
