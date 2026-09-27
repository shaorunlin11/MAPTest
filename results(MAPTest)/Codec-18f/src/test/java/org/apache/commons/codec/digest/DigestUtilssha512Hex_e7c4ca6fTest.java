package org.apache.commons.codec.digest;

import org.junit.Test;
import static org.junit.Assert.*;

public class DigestUtilssha512Hex_e7c4ca6fTest {

    @Test
    public void testSha512Hex() {
        String input = "Hello, World!";
        String expectedHash = "374d794a95cdcfd8b35993185fef9ba368f160d8daf432d08ba9f1ed1e5abe6cc69291e0fa2fe0006a52570ef18c19def4e617c33ce52ef0a6e5fbe318cb0387";
        String result = DigestUtils.sha512Hex(input);
        assertNotNull(result);
        assertEquals(expectedHash, result);
    }

    @Test
    public void testSha512Hex_NullInput() {
        String input = null;
        try {
            DigestUtils.sha512Hex(input);
            fail("Expected NullPointerException was not thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
