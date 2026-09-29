package com.fasterxml.jackson.core;
import org.junit.Test;
import org.junit.Assert;
public class Base64Variant_reportInvalidBase64Test {
    @Test
    public void testReportInvalidBase64WithWhitespace() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        try {
            variant._reportInvalidBase64(' ', 0, null);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(e.getMessage().contains("Illegal white space character (code 0x20) as character #1 of 4-char base64 unit"));
        }
    }

    @Test
    public void testReportInvalidBase64WithUnexpectedPadding() throws Exception {
        Base64Variant variant = new Base64Variant("test", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 0);
        try {
            variant._reportInvalidBase64('=', 0, null);
            Assert.fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(e.getMessage().contains("Unexpected padding character ('=') as character #1 of 4-char base64 unit"));
        }
    }



}
