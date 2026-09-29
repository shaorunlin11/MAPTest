package org.apache.commons.codec.language.bm;

import org.junit.Test;
import org.junit.Assert;

public class PhoneticEnginegetMaxPhonemesTest {
    @Test
    public void testGetMaxPhonemes() throws Exception {
        // Create a mock NameType and RuleType
        final NameType nameType = NameType.values()[0];
        final RuleType ruleType = RuleType.values()[0];
        final boolean concat = false;
        final int maxPhonemes = 42;

        // Create a PhoneticEngine instance with the specified maxPhonemes
        PhoneticEngine engine = new PhoneticEngine(nameType, ruleType, concat, maxPhonemes);

        // Verify that getMaxPhonemes returns the expected value
        Assert.assertEquals(maxPhonemes, engine.getMaxPhonemes());
    }
}
