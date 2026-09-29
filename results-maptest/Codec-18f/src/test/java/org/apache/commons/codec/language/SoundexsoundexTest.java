package org.apache.commons.codec.language;

import org.junit.Test;
import org.junit.Assert;

public class SoundexsoundexTest {

    @Test
    public void testSoundexNullInput() {
        Soundex soundex = new Soundex();
        String result = soundex.soundex(null);
        Assert.assertNull(result);
    }

    @Test
    public void testSoundexEmptyInput() {
        Soundex soundex = new Soundex();
        String result = soundex.soundex("");
        Assert.assertEquals("", result);
    }

    @Test
    public void testSoundexSingleCharacter() {
        Soundex soundex = new Soundex();
        String result = soundex.soundex("A");
        Assert.assertEquals("A000", result);
    }

    @Test
    public void testSoundexMultipleCharacters() {
        Soundex soundex = new Soundex();
        String result = soundex.soundex("Robert");
        Assert.assertEquals("R163", result);
    }

    @Test
    public void testSoundexWithNonAlphabeticalCharacters() {
        Soundex soundex = new Soundex();
        String result = soundex.soundex("R3b3rt");
        Assert.assertEquals("R163", result);
    }

    @Test
    public void testSoundexWithPadding() {
        Soundex soundex = new Soundex();
        String result = soundex.soundex("John");
        Assert.assertEquals("J500", result);
    }

    @Test
    public void testSoundexWithSpecialCharacters() {
        Soundex soundex = new Soundex();
        String result = soundex.soundex("Joh#n");
        Assert.assertEquals("J500", result);
    }

    @Test
    public void testSoundexWithMultipleMappedCharacters() {
        Soundex soundex = new Soundex();
        String result = soundex.soundex("Ashcraft");
        Assert.assertEquals("A261", result);
    }
}
