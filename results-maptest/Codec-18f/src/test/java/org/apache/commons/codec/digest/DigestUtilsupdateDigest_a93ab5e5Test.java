package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.junit.Assert;
import java.util.Arrays;

public class DigestUtilsupdateDigest_a93ab5e5Test {
    private File tempFile;

    @Before
    public void setUp() throws IOException {
        tempFile = new File("testfile.txt");
        tempFile.createNewFile();
    }

    @After
    public void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    public void testUpdateDigestWithValidFile() throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        File data = tempFile;

        MessageDigest result = DigestUtils.updateDigest(digest, data);

        // Verify that the digest was updated
        Assert.assertNotNull(result);
        // Verify that the digest values are different
        Assert.assertTrue(Arrays.equals(digest.digest(), result.digest()));
    }

    @Test
    public void testUpdateDigestWithNullFile() throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        File data = null;

        try {
            DigestUtils.updateDigest(digest, data);
            Assert.fail("Expected NullPointerException was not thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }

    @Test
    public void testUpdateDigestWithInvalidFile() throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        File data = new File("nonexistentfile.txt");

        try {
            DigestUtils.updateDigest(digest, data);
            Assert.fail("Expected IOException was not thrown");
        } catch (IOException e) {
            // Expected exception
        }
    }
}
