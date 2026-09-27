package org.apache.commons.codec.digest;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.nio.file.Files;
import org.junit.Test;
import org.junit.Assert;

import java.security.NoSuchAlgorithmException;


public class DigestUtilsdigestAsHex_a93562afTest {

    @Test
    public void testDigestAsHex() throws IOException {
        // Create a temporary file with known content
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();
        String testData = "Hello, World!";
        Files.write(tempFile.toPath(), testData.getBytes());

        // Create a DigestUtils instance with a SHA-1 message digest
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            DigestUtils digestUtils = new DigestUtils(messageDigest);

            // Compute the hex digest of the file
            String hexDigest = digestUtils.digestAsHex(tempFile);

            // Verify the result is not null and has the expected length
            Assert.assertNotNull(hexDigest);
            Assert.assertEquals(40, hexDigest.length());
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e);
        }
    }

    @Test(expected = IOException.class)
    public void testDigestAsHex_InvalidFile() throws IOException {
        // Create a non-existent file
        File invalidFile = new File("invalid_file.txt");

        // Create a DigestUtils instance with a SHA-1 message digest
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            DigestUtils digestUtils = new DigestUtils(messageDigest);

            // Attempt to compute the hex digest of the invalid file
            digestUtils.digestAsHex(invalidFile);
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e);
        }
    }
}
