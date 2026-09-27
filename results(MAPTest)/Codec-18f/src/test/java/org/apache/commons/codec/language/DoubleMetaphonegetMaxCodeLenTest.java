package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleMetaphonegetMaxCodeLenTest {
    @Test
    public void testGetMaxCodeLen() throws Exception {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        int result = doubleMetaphone.getMaxCodeLen();
        assertEquals(4, result);
    }
}
