package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;

public class Nysiisencode_e75f1fa7Test {
    @Test
    public void testEncode() {
        Nysiis nysiis = new Nysiis();
        String encoded = nysiis.encode("test");
        assertNotNull("encode method should not return null", encoded);
        assertEquals("encode method should delegate to nysiis", nysiis.nysiis("test"), encoded);
    }
}
