package org.apache.commons.codec.digest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import static org.junit.Assert.*;

public class DigestUtilssha1_28ffc9e0Test {

    @Test
    public void testSha1WithEmptyInputStream() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        byte[] result = DigestUtils.sha1(inputStream);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test
    public void testSha1WithNonEmptyInputStream() throws IOException {
        String input = "Hello, World!";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        byte[] result = DigestUtils.sha1(inputStream);
        assertNotNull(result);
        assertEquals(20, result.length);
    }

    @Test(expected = IOException.class)
    public void testSha1WithThrowingInputStream() throws IOException {
        InputStream inputStream = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Simulated error");
            }
        };
        DigestUtils.sha1(inputStream);
    }
}
