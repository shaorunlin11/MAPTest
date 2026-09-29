package org.apache.commons.codec.digest;

import org.junit.Test;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HmacUtilshmacMd5Hex_0352c20fTest {

    @Test
    public void testHmacMd5Hex() throws IOException {
        byte[] key = "secret".getBytes();
        String input = "test data";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        String result = HmacUtils.hmacMd5Hex(key, inputStream);

        Assert.assertNotNull("Result should not be null", result);
        Assert.assertTrue("Result should be a hexadecimal string", result.matches("[a-fA-F0-9]+"));
    }
}
