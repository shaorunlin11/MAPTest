package org.apache.commons.codec.digest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class DigestUtilsSha384HexZeroCoverageTest {
    @Test
    public void testSha384Hex() throws IOException {
        String input = "test data";
        InputStream data = new ByteArrayInputStream(input.getBytes());

        String result = DigestUtils.sha384Hex(data);

        assertEquals("29901176dc824ac3fd22227677499f02e4e69477ccc501593cc3dc8c6bfef73a08dfdf4a801723c0479b74d6f1abc372", result);
    }
}
