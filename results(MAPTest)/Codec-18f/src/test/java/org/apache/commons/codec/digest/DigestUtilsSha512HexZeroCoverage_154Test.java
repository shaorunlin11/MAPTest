package org.apache.commons.codec.digest;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

import org.apache.commons.codec.binary.Hex;

public class DigestUtilsSha512HexZeroCoverage_154Test {
    @Test
    public void testSha512HexWithNonNullData() throws Exception {
        String input = "test";
        InputStream data = new ByteArrayInputStream(input.getBytes());

        String result = DigestUtils.sha512Hex(data);

        assertEquals("Expected non-null result from sha512Hex", "ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff", result);
    }
}
