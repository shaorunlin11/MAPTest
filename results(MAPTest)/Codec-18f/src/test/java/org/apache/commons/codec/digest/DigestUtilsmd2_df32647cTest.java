package org.apache.commons.codec.digest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import static org.junit.Assert.*;

public class DigestUtilsmd2_df32647cTest {

    @Test
    public void testMd2WithNonNullInputStream() throws IOException {
        String input = "Hello, world!";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        byte[] result = DigestUtils.md2(inputStream);
        assertNotNull("Result should not be null", result);
        assertEquals("Result length should match MD2 hash length", 16, result.length);
    }

    @Test(expected = IOException.class)
    public void testMd2WithIOException() throws IOException {
        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Simulated I/O error");
            }
        };
        DigestUtils.md2(inputStream);
    }
}
