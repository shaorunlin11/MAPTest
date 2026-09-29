package org.apache.commons.codec.digest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DigestUtilsmd5Hex_5999ccdeTest {

    @Test
    public void testMd5HexWithValidInputStream() throws IOException {
        String input = "Hello, world!";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        String expectedHash = "6cd3556deb0da54bca060b4c39479839";
        String result = DigestUtils.md5Hex(inputStream);
        assertEquals(expectedHash, result);
    }

    @Test
    public void testMd5HexWithEmptyInputStream() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        String expectedHash = "d41d8cd98f00b204e9800998ecf8427e";
        String result = DigestUtils.md5Hex(inputStream);
        assertEquals(expectedHash, result);
    }

    @Test(expected = IOException.class)
    public void testMd5HexWithIOException() throws IOException {
        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Simulated IO error");
            }
        };
        try {
            DigestUtils.md5Hex(inputStream);
            fail("Expected IOException was not thrown");
        } catch (IOException e) {
            // Expected exception
            throw e;
        }
    }
}
