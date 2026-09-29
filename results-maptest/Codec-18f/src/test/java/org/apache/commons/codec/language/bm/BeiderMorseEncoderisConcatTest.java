package org.apache.commons.codec.language.bm;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class BeiderMorseEncoderisConcatTest {
    private BeiderMorseEncoder encoder;

    @Before
    public void setUp() {
        encoder = new BeiderMorseEncoder();
    }

    @Test
    public void testIsConcat() {
        boolean result = encoder.isConcat();
        Assert.assertTrue(result);
    }
}
