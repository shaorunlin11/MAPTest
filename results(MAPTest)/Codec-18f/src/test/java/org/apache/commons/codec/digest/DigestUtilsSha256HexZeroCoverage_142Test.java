package org.apache.commons.codec.digest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class DigestUtilsSha256HexZeroCoverage_142Test {
    @Test
    public void testSha256HexWithNonNullInputStream() throws Exception {
        String input = "test";
        InputStream data = new ByteArrayInputStream(input.getBytes());
        String result = DigestUtils.sha256Hex(data);
        assertEquals("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", result);
    }
}
