package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;

public class SerializedStringappendUnquotedTest {
    @Test
    public void testAppendUnquotedWithSufficientBuffer() {
        SerializedString serializedString = new SerializedString("test");
        char[] buffer = new char[10];
        int offset = 0;
        int result = serializedString.appendUnquoted(buffer, offset);
        Assert.assertEquals("Should return the length of the string", 4, result);
        Assert.assertEquals("Should copy the string into the buffer", 't', buffer[0]);
        Assert.assertEquals("Should copy the string into the buffer", 'e', buffer[1]);
        Assert.assertEquals("Should copy the string into the buffer", 's', buffer[2]);
        Assert.assertEquals("Should copy the string into the buffer", 't', buffer[3]);
    }

    @Test
    public void testAppendUnquotedWithInsufficientBuffer() {
        SerializedString serializedString = new SerializedString("test");
        char[] buffer = new char[3];
        int offset = 0;
        int result = serializedString.appendUnquoted(buffer, offset);
        Assert.assertEquals("Should return -1 when buffer is too small", -1, result);
    }

    @Test
    public void testAppendUnquotedWithOffset() {
        SerializedString serializedString = new SerializedString("test");
        char[] buffer = new char[10];
        int offset = 2;
        int result = serializedString.appendUnquoted(buffer, offset);
        Assert.assertEquals("Should return the length of the string", 4, result);
        Assert.assertEquals("Should copy the string into the buffer starting at the given offset", 't', buffer[2]);
        Assert.assertEquals("Should copy the string into the buffer starting at the given offset", 'e', buffer[3]);
        Assert.assertEquals("Should copy the string into the buffer starting at the given offset", 's', buffer[4]);
        Assert.assertEquals("Should copy the string into the buffer starting at the given offset", 't', buffer[5]);
    }
}
