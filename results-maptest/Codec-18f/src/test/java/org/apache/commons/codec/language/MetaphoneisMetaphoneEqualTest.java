package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;

public class MetaphoneisMetaphoneEqualTest {

    @Test
    public void testIsMetaphoneEqual_SameMetaphone_ReturnsTrue() {
        Metaphone metaphone = new Metaphone();
        assertTrue(metaphone.isMetaphoneEqual("hello", "helo"));
    }

    @Test
    public void testIsMetaphoneEqual_DifferentMetaphone_ReturnsFalse() {
        Metaphone metaphone = new Metaphone();
        assertFalse(metaphone.isMetaphoneEqual("hello", "world"));
    }

    @Test
    public void testIsMetaphoneEqual_NullStrings_ReturnsFalse() {
        Metaphone metaphone = new Metaphone();
        assertFalse(metaphone.isMetaphoneEqual(null, "test"));
    }

    @Test
    public void testIsMetaphoneEqual_EmptyStrings_ReturnsTrue() {
        Metaphone metaphone = new Metaphone();
        assertTrue(metaphone.isMetaphoneEqual("", ""));
    }

    @Test
    public void testIsMetaphoneEqual_OneEmptyString_ReturnsFalse() {
        Metaphone metaphone = new Metaphone();
        assertFalse(metaphone.isMetaphoneEqual("", "test"));
    }
}
