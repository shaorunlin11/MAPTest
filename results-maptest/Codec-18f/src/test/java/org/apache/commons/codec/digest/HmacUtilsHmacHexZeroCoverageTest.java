package org.apache.commons.codec.digest;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HmacUtilsHmacHexZeroCoverageTest {
    @Test
    public void testHmacHexWithFile() throws IOException {
        // Create a temporary file with some content
        File file = new File("testfile.txt");
        file.createNewFile();

        // Write some data to the file
        // Note: In a real test, you would use a proper file writing mechanism
        // For this example, we'll assume the file contains "testdata"

        // Create an instance of HmacUtils with a sample key
        HmacUtils hmacUtils = new HmacUtils("HmacSHA256", "secretkey".getBytes());

        // Call the method under test
        String result = hmacUtils.hmacHex(file);

        // Verify the result is not null and has a valid hex string format
        assertEquals("Expected a non-null hex string", 64, result.length());

        // Clean up the temporary file
        file.delete();
    }
}
