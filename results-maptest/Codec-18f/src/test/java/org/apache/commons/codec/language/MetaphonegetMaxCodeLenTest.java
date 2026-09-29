package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;

public class MetaphonegetMaxCodeLenTest {
    @Test
    public void testGetMaxCodeLen() throws Exception {
        Metaphone metaphone = new Metaphone();
        assertEquals(4, metaphone.getMaxCodeLen());
    }
}
