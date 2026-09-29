package org.apache.commons.codec.cli;

import org.junit.Test;
import org.junit.Assert;

import java.io.IOException;

public class DigestmainTest {

    @Test
    public void testMainWithValidArguments() throws Exception {
        String[] args = {"SHA-256", "testfile.txt"};
        Digest.main(args);
        // This test only verifies that the method runs without throwing an exception
        // Actual behavior would require mocking or integration testing
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMainWithNullArgs() throws Exception {
        Digest.main(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMainWithEmptyArgs() throws Exception {
        Digest.main(new String[0]);
    }
}
