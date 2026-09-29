package org.apache.commons.codec.language;

import org.junit.Test;

public class MetaphoneMetaphoneZeroCoverageTest {
    @Test
    public void testMetaphoneTargetLines93() {
        Metaphone metaphone = new Metaphone();
        String txt = "example";
        String result = metaphone.metaphone(txt);
    }

@Test
    public void testMetaphoneTargetLines95() {
        Metaphone metaphone = new Metaphone();
        String txt = null;
        String result = metaphone.metaphone(txt);
    }

@Test
    public void testMetaphoneTargetLines95EmptyString() {
        Metaphone metaphone = new Metaphone();
        String txt = "";
        String result = metaphone.metaphone(txt);
    }

@Test
    public void testMetaphoneTargetLines99() {
        Metaphone metaphone = new Metaphone();
        String txt = "A";
        String result = metaphone.metaphone(txt);
    }
}
