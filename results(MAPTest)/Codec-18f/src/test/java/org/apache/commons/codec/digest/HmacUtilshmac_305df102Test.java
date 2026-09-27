package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.StringUtils;

public class HmacUtilshmac_305df102Test {
    private File testFile;
    private HmacUtils hmacUtils;

    @Before
    public void setUp() throws Exception {
        // Create a temporary file in the system's temporary directory to avoid path issues
        testFile = File.createTempFile("testfile", ".txt");
        hmacUtils = new HmacUtils("HmacSHA256", "testkey".getBytes());
    }

    @After
    public void tearDown() throws Exception {
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void testHmac_File() throws Exception {
        byte[] result = hmacUtils.hmac(new FileInputStream(testFile));
        Assert.assertNotNull("HMAC result should not be null", result);
        Assert.assertTrue("HMAC result should have non-zero length", result.length > 0);
    }

    @Test
    public void testHmac_FileWithInvalidPath() throws Exception {
        File invalidFile = new File("/invalid/path/to/file.txt");
        try {
            hmacUtils.hmac(new FileInputStream(invalidFile));
            Assert.fail("Expected IOException was not thrown");
        } catch (IOException e) {
            // Expected exception
        }
    }

    @Test
    public void testHmac_FileWithNull() throws Exception {
        try {
            hmacUtils.hmac((File) null);
            Assert.fail("Expected NullPointerException was not thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }

@Test
    public void testHmac_File_ReachTargetLine1067() throws Exception {
        // Ensure the file exists and is not null
        Assert.assertTrue("Test file should exist", testFile.exists());
        Assert.assertNotNull("Test file should not be null", testFile);

        // Execute the method under test
        byte[] result = hmacUtils.hmac(testFile);
        Assert.assertNotNull("HMAC result should not be null", result);
        Assert.assertTrue("HMAC result should have non-zero length", result.length > 0);
    }
}
