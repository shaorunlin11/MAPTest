package org.apache.commons.codec.digest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.Test;
import org.junit.Assert;

public class DigestUtilsdigest_80c7b2afTest {

    @Test
    public void testDigest_File() throws NoSuchAlgorithmException, IOException {
        // Create a temporary file with known content
        File tempFile = new File("testfile.txt");
        tempFile.createNewFile();

        // Write known content to the file
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("test content");
        }

        // Create a MessageDigest instance
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        // Create a DigestUtils instance
        DigestUtils digestUtils = new DigestUtils(messageDigest);

        // Call the method under test
        byte[] result = digestUtils.digest(tempFile);

        // Assert that the result is not null
        Assert.assertNotNull(result);

        // Clean up
        tempFile.delete();
    }
}
