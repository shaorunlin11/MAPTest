package org.apache.commons.codec.digest;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.Assert.assertEquals;

public class DigestUtilsDigestAsHexZeroCoverageTest {
    @Test
    public void testDigestAsHex() throws Exception {
        // Create a DigestUtils instance with a specific message digest
        DigestUtils digestUtils = new DigestUtils("MD5");

        // Create a sample input stream
        String inputData = "test data";
        InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());

        // Call the method under test
        String result = digestUtils.digestAsHex(inputStream);

        // Verify the result is not null
        assertEquals("Expected non-null result", true, result != null);

        // Verify the result is a hex string
        assertEquals("Expected hex string", true, result.matches("[a-fA-F0-9]+"));
    }
}
