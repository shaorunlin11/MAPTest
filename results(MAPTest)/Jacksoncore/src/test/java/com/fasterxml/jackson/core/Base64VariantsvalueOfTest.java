package com.fasterxml.jackson.core;
import org.junit.Test;
import org.junit.Assert;
public class Base64VariantsvalueOfTest {
    @Test
    public void testValueOfWithMime() {
        Base64Variant result = Base64Variants.valueOf("MIME");
        Assert.assertNotNull(result);
        Assert.assertEquals("MIME", result._name);
    }


    @Test
    public void testValueOfWithPem() {
        Base64Variant result = Base64Variants.valueOf("PEM");
        Assert.assertNotNull(result);
        Assert.assertEquals("PEM", result._name);
    }


    @Test
    public void testValueOfWithNullName() {
        try {
            Base64Variants.valueOf(null);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(e.getMessage().contains("<null>"));
        }
    }

    @Test
    public void testValueOfWithUnknownName() {
        try {
            Base64Variants.valueOf("UNKNOWN");
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(e.getMessage().contains("'UNKNOWN'"));
        }
    }

@Test
    public void testValueOfWithMimeNoLinefeeds() {
        Base64Variant result = Base64Variants.valueOf(Base64Variants.MIME_NO_LINEFEEDS._name);
        Assert.assertNotNull(result);
        Assert.assertEquals(Base64Variants.MIME_NO_LINEFEEDS._name, result._name);
    }

@Test
    public void testValueOfWithModifiedForUrl() {
        Base64Variant result = Base64Variants.valueOf(Base64Variants.MODIFIED_FOR_URL._name);
        Assert.assertNotNull(result);
        Assert.assertEquals(Base64Variants.MODIFIED_FOR_URL._name, result._name);
    }
}
