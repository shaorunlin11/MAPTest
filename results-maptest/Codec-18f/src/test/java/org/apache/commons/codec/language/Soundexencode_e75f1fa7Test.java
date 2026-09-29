package org.apache.commons.codec.language;

import org.junit.Test;
import org.junit.Assert;

public class Soundexencode_e75f1fa7Test {

    @Test
    public void testEncode() {
        Soundex soundex = new Soundex();
        String result = soundex.encode("example");
        Assert.assertNotNull(result);
    }

    @Test
    public void testEncodeWithNullInput() {
        Soundex soundex = new Soundex();
        String result = soundex.encode(null);
        Assert.assertNull(result);
    }

    @Test
    public void testEncodeWithEmptyString() {
        Soundex soundex = new Soundex();
        String result = soundex.encode("");
        Assert.assertEquals("", result);
    }

    @Test
    public void testEncodeWithSingleCharacter() {
        Soundex soundex = new Soundex();
        String result = soundex.encode("a");
        Assert.assertEquals("A000", result);
    }

    @Test
    public void testEncodeWithMultipleCharacters() {
        Soundex soundex = new Soundex();
        String result = soundex.encode("hello");
        Assert.assertEquals("H400", result);
    }
}
