package org.apache.commons.codec.digest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DigestUtilssha1Hex_e9ba5446Test {

    @Test
    public void testSha1HexWithEmptyInput() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(new byte[0]);
        String result = DigestUtils.sha1Hex((InputStream) inputStream);
        assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", result);
    }

    @Test
    public void testSha1HexWithKnownInput() throws IOException {
        InputStream inputStream = new ByteArrayInputStream("Hello, World!".getBytes());
        String result = DigestUtils.sha1Hex((InputStream) inputStream);
        assertEquals("0a0a9f2a6772942557ab5355d76af442f8f65e01", result);
    }

    @Test
    public void testSha1HexWithNullInput() {
        try {
            DigestUtils.sha1Hex((InputStream) null);
            fail("Expected NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Expected exception for null input
        } catch (IOException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
}
