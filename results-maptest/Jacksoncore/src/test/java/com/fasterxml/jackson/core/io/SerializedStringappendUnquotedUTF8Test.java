package com.fasterxml.jackson.core.io;
import org.junit.Test;
import org.junit.Assert;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
public class SerializedStringappendUnquotedUTF8Test {
    @Test
    public void testAppendUnquotedUTF8_BufferTooSmall() throws Exception {
        SerializedString ss = new SerializedString("test");
        byte[] buffer = new byte[3];
        int result = ss.appendUnquotedUTF8(buffer, 0);
        Assert.assertEquals(-1, result);
    }



    @Test
    public void testAppendUnquotedUTF8_Offset() throws Exception {
        SerializedString ss = new SerializedString("test");
        byte[] buffer = new byte[10];
        int result = ss.appendUnquotedUTF8(buffer, 3);
        Assert.assertEquals(4, result);
        Assert.assertArrayEquals(new byte[]{0, 0, 0, 116, 101, 115, 116, 0, 0, 0}, buffer);
    }
}
