package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class HmacUtilshmacMd5_f4a18792Test {

    @Test
    public void testHmacMd5() {
        byte[] key = "secret".getBytes();
        byte[] valueToDigest = "data".getBytes();

        byte[] result = HmacUtils.hmacMd5(key, valueToDigest);

        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.length == 0);
    }
}
