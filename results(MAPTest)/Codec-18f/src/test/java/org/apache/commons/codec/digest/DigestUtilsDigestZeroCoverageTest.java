package org.apache.commons.codec.digest;

import org.junit.Test;

import java.io.File;
import java.security.MessageDigest;

public class DigestUtilsDigestZeroCoverageTest {
    @Test
    public void testDigestWithFile() throws Exception {
        // Create a temporary file with some content
        File data = new File("testfile.txt");
        data.createNewFile();

        // Initialize messageDigest with a valid algorithm
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        // Call the method under test
        byte[] result = DigestUtils.digest(messageDigest, data);

        // Add assertions if needed
    }
}
