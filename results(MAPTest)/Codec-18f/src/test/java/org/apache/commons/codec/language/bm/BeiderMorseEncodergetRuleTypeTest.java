package org.apache.commons.codec.language.bm;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BeiderMorseEncodergetRuleTypeTest {
    @Test
    public void testGetRuleType() throws Exception {
        BeiderMorseEncoder encoder = new BeiderMorseEncoder();
        RuleType result = encoder.getRuleType();
        assertEquals(RuleType.APPROX, result);
    }
}
