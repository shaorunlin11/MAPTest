package org.apache.commons.codec.language.bm;

import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.commons.codec.language.bm.Rule.RPattern;

public class RuleRPatternTest {
    @Test
    public void testAllStringsRmatcherIsMatch() {
        RPattern allStringsRmatcher = Rule.ALL_STRINGS_RMATCHER;
        assertTrue(allStringsRmatcher.isMatch("anyString"));
        assertTrue(allStringsRmatcher.isMatch(""));
        assertTrue(allStringsRmatcher.isMatch(new StringBuilder("test")));
    }
}
