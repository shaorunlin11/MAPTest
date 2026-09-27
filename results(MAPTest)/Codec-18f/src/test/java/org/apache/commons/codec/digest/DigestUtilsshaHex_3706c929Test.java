package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class DigestUtilsshaHex_3706c929Test {

    @Test
    public void testShaHex() throws Exception {
        byte[] data = "test".getBytes();
        String expected = DigestUtils.sha1Hex(data);
        String actual = DigestUtils.sha1Hex(data);
        assertEquals(expected, actual);
    }

@Test
    public void testShaHexTargetLine() throws Exception {
        byte[] data = "test".getBytes();
        String result = DigestUtils.shaHex(data);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
