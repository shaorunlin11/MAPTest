package org.apache.commons.codec.language;

import org.junit.Test;
import org.junit.Assert;

public class RefinedSoundexsoundexTest {

    @Test
    public void testSoundex_NullInput() {
        RefinedSoundex soundex = new RefinedSoundex();
        Assert.assertNull(soundex.soundex(null));
    }

    @Test
    public void testSoundex_EmptyInput() {
        RefinedSoundex soundex = new RefinedSoundex();
        Assert.assertEquals("", soundex.soundex(""));
    }

    @Test
    public void testSoundex_SingleCharacter() {
        RefinedSoundex soundex = new RefinedSoundex();
        Assert.assertEquals("A0", soundex.soundex("A"));
    }

    @Test
    public void testSoundex_MultipleCharacters() {
        RefinedSoundex soundex = new RefinedSoundex();
        Assert.assertEquals("A01", soundex.soundex("AB"));
    }

    @Test
    public void testSoundex_DuplicateCodes() {
        RefinedSoundex soundex = new RefinedSoundex();
        Assert.assertEquals("A01", soundex.soundex("AAB"));
    }

    @Test
    public void testSoundex_CleanedStringEmpty() {
        RefinedSoundex soundex = new RefinedSoundex();
        Assert.assertEquals("", soundex.soundex("   "));
    }

    @Test
    public void testSoundex_CaseInsensitive() {
        RefinedSoundex soundex = new RefinedSoundex();
        Assert.assertEquals("A01", soundex.soundex("aB"));
    }

    @Test
    public void testSoundex_MixedCharacters() {
        RefinedSoundex soundex = new RefinedSoundex();
        Assert.assertEquals("A013", soundex.soundex("ABC"));
    }

    @Test
    public void testSoundex_WithSpecialCharacters() {
        RefinedSoundex soundex = new RefinedSoundex();
        Assert.assertEquals("A01", soundex.soundex("A!B"));
    }
}
