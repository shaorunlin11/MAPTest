package org.apache.commons.codec.language.bm;

import org.junit.Test;
import static org.junit.Assert.*;

public class BeiderMorseEncodergetNameTypeTest {
    @Test
    public void testGetNameType() throws Exception {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        NameType result = encoder.getNameType();
        assertNotNull("getNameType should not return null", result);
    }
}
