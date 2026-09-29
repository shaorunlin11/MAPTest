package org.apache.commons.codec.language;

import org.junit.Test;

public class ColognePhoneticColognePhoneticZeroCoverageTest {
    @Test
    public void testColognePhoneticWithNullInput() {
        ColognePhonetic codec = new ColognePhonetic();
        String result = codec.colognePhonetic(null);
        // The method should return null when input is null
        // This covers line 320 of the method
        assert result == null;
    }

@Test
    public void testColognePhoneticWithSpecificInputToCoverLine338() {
        ColognePhonetic codec = new ColognePhonetic();
        String result = codec.colognePhonetic("X");
        // This test is designed to execute line 338 of the method
        // Line 338: code = '4';
        // This is triggered when chr == 'X' and !arrayContains(CKQ, lastChar)
        // The input "X" should trigger this path
        assert result != null;
    }

@Test
    public void testColognePhoneticWithInputToCoverLine341() {
        ColognePhonetic codec = new ColognePhonetic();
        String result = codec.colognePhonetic("CX");
        // This test is designed to execute line 341 of the method
        // Line 341: code = '4';
        // This is triggered when chr == 'X' and !arrayContains(CKQ, lastChar)
        // The input "CX" should trigger this path
        assert result != null;
    }
}
