package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;

public class Caverphoneencode_e75f1fa7Test {

    @Test
    public void testEncode() {
        Caverphone caverphone = new Caverphone();
        assertEquals("ANPTSTRNK1", caverphone.encode("input_string"));
    }
}
