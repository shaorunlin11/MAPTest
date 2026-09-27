package org.apache.commons.codec.language.bm;

import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.commons.codec.language.bm.Rule;

public class RulegetPatternTest {
    @Test
    public void testGetPattern() throws Exception {
        // Create a Rule instance with a known pattern
        Rule rule = new Rule("testPattern", "leftContext", "rightContext", null);

        // Verify that getPattern returns the expected value
        assertEquals("testPattern", rule.getPattern());
    }
}
