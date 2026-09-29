package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VarianthashCodeTest {
    @Test
    public void testHashCode() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        int hashCode = variant.hashCode();
        assertEquals("Hash code should match the name's hash code", variant._name.hashCode(), hashCode);
    }
}
