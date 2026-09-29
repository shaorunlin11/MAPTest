package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class Base64VariantsgetDefaultVariantTest {
    @Test
    public void testGetDefaultVariantReturnsMIME_NO_LINEFEEDS() {
        Base64Variant result = Base64Variants.getDefaultVariant();
        assertNotNull("getDefaultVariant should not return null", result);
        assertSame("getDefaultVariant should return the MIME_NO_LINEFEEDS instance", Base64Variants.MIME_NO_LINEFEEDS, result);
    }
}
