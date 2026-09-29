package org.apache.commons.codec.language;

import org.junit.Test;
import org.junit.Assert;

public class SoundexgetMaxLengthTest {
    @Test
    public void testGetMaxLength() throws Exception {
        Soundex soundex = new Soundex();
        int maxLength = soundex.getMaxLength();
        Assert.assertEquals(4, maxLength);
    }
}
