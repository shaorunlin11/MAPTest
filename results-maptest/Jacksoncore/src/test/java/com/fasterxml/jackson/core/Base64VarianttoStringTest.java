package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VarianttoStringTest {
    @Test
    public void testToString_returnsName() throws Exception {
        Base64Variant variant = new Base64Variant("testName", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        assertEquals("testName", variant.toString());
    }
}
