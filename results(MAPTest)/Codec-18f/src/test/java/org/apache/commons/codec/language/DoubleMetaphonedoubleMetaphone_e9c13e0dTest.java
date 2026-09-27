package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleMetaphonedoubleMetaphone_e9c13e0dTest {

    @Test
    public void testDoubleMetaphoneWithNullInput() throws Exception {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        assertNull(doubleMetaphone.doubleMetaphone(null, false));
    }

    @Test
    public void testDoubleMetaphoneWithEmptyString() throws Exception {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        assertNull(doubleMetaphone.doubleMetaphone("", false));
    }

    @Test
    public void testDoubleMetaphoneWithSimpleWord() throws Exception {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        assertEquals("JN", doubleMetaphone.doubleMetaphone("John", false));
    }

    @Test
    public void testDoubleMetaphoneWithSpecialCharacters() throws Exception {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        assertEquals("SN", doubleMetaphone.doubleMetaphone("Cin", false));
        assertEquals("N", doubleMetaphone.doubleMetaphone("No", false));
    }

    @Test
    public void testDoubleMetaphoneWithSilentStart() throws Exception {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        assertEquals("NM", doubleMetaphone.doubleMetaphone("Gnome", false));
    }

    @Test
    public void testDoubleMetaphoneWithAlternateTrue() throws Exception {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        assertEquals("AN", doubleMetaphone.doubleMetaphone("John", true));
    }

    @Test
    public void testDoubleMetaphoneWithMultipleVowels() throws Exception {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        assertEquals("JN", doubleMetaphone.doubleMetaphone("Joan", false));
    }

    @Test
    public void testDoubleMetaphoneWithConsonants() throws Exception {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        assertEquals("PK", doubleMetaphone.doubleMetaphone("Bk", false));
    }

    @Test
    public void testDoubleMetaphoneWithComplexWord() throws Exception {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        assertEquals("KSTR", doubleMetaphone.doubleMetaphone("Kestrel", false));
    }

@Test
    public void testDoubleMetaphoneWithTargetLines98() throws Exception {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        doubleMetaphone.setMaxCodeLen(5);

        // Ensure isSlavoGermanic returns false and isSilentStart returns false
        // This can be achieved by using a value that doesn't match any of the patterns
        String value = "Example";

        // Execute the method with the required conditions
        String result = doubleMetaphone.doubleMetaphone(value, false);

        // Add an assertion to ensure the method executed as expected
        assertNotNull(result);
    }

@Test
    public void testDoubleMetaphoneWithTargetLines124() throws Exception {
        DoubleMetaphone doubleMetaphone = new DoubleMetaphone();
        doubleMetaphone.setMaxCodeLen(5);

        // Ensure isSlavoGermanic returns true and isSilentStart returns false
        // This can be achieved by using a value that matches the Slavo-Germanic pattern
        String value = "Wolfgang";

        // Execute the method with the required conditions
        String result = doubleMetaphone.doubleMetaphone(value, false);

        // Add an assertion to ensure the method executed as expected
        assertNotNull(result);
    }
}
