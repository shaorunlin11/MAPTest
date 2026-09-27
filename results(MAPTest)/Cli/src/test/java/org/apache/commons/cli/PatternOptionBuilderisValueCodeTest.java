package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class PatternOptionBuilderisValueCodeTest {

    @Test
    public void testIsValueCode() {
        assertTrue(PatternOptionBuilder.isValueCode('@'));
        assertTrue(PatternOptionBuilder.isValueCode(':'));
        assertTrue(PatternOptionBuilder.isValueCode('%'));
        assertTrue(PatternOptionBuilder.isValueCode('+'));
        assertTrue(PatternOptionBuilder.isValueCode('#'));
        assertTrue(PatternOptionBuilder.isValueCode('<'));
        assertTrue(PatternOptionBuilder.isValueCode('>'));
        assertTrue(PatternOptionBuilder.isValueCode('*'));
        assertTrue(PatternOptionBuilder.isValueCode('/'));
        assertTrue(PatternOptionBuilder.isValueCode('!'));

        assertFalse(PatternOptionBuilder.isValueCode('a'));
        assertFalse(PatternOptionBuilder.isValueCode('0'));
        assertFalse(PatternOptionBuilder.isValueCode(' '));
        assertFalse(PatternOptionBuilder.isValueCode('\t'));
        assertFalse(PatternOptionBuilder.isValueCode('A'));
        assertFalse(PatternOptionBuilder.isValueCode('Z'));
        assertFalse(PatternOptionBuilder.isValueCode('z'));
        assertFalse(PatternOptionBuilder.isValueCode('9'));
        assertFalse(PatternOptionBuilder.isValueCode('$'));
        assertFalse(PatternOptionBuilder.isValueCode('&'));
        assertFalse(PatternOptionBuilder.isValueCode('='));
        assertFalse(PatternOptionBuilder.isValueCode('['));
        assertFalse(PatternOptionBuilder.isValueCode(']'));
        assertFalse(PatternOptionBuilder.isValueCode('{'));
        assertFalse(PatternOptionBuilder.isValueCode('}'));
        assertFalse(PatternOptionBuilder.isValueCode('\\'));
        assertFalse(PatternOptionBuilder.isValueCode('|'));
        assertFalse(PatternOptionBuilder.isValueCode('^'));
        assertFalse(PatternOptionBuilder.isValueCode('~'));
    }
}
