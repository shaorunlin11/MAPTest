package org.apache.commons.codec.digest;

import org.junit.Test;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.StringUtils;

import static org.junit.Assert.assertArrayEquals;

public class HmacUtilshmacSha512_f0dce6e9Test {

    @Test
    public void testHmacSha512() throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] key = "secret".getBytes();
        byte[] valueToDigest = "data".getBytes();

        byte[] expected = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, key).hmac(valueToDigest);
        byte[] actual = HmacUtils.hmacSha512(key, valueToDigest);

        assertArrayEquals(expected, actual);
    }
}
