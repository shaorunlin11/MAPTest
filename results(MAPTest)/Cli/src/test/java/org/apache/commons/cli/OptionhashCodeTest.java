package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionhashCodeTest {

    @Test
    public void testHashCodeWithOptAndLongOpt() {
        Option option = new Option("a", "arg", false, "description");
        int hashCode = option.hashCode();
        assertEquals("Hash code should be based on opt and longOpt", "a".hashCode() * 31 + "arg".hashCode(), hashCode);
    }

    @Test
    public void testHashCodeWithOptNull() {
        Option option = new Option(null, "arg", false, "description");
        int hashCode = option.hashCode();
        assertEquals("Hash code should be based on longOpt when opt is null", 0 * 31 + "arg".hashCode(), hashCode);
    }

    @Test
    public void testHashCodeWithLongOptNull() {
        Option option = new Option("a", null, false, "description");
        int hashCode = option.hashCode();
        assertEquals("Hash code should be based on opt when longOpt is null", "a".hashCode() * 31 + 0, hashCode);
    }

    @Test
    public void testHashCodeWithBothNull() {
        Option option = new Option(null, null, false, "description");
        int hashCode = option.hashCode();
        assertEquals("Hash code should be 0 when both opt and longOpt are null", 0, hashCode);
    }

    @Test
    public void testHashCodeConsistency() {
        Option option1 = new Option("a", "arg", false, "description");
        Option option2 = new Option("a", "arg", false, "description");
        assertEquals("Hash codes of equal objects should be the same", option1.hashCode(), option2.hashCode());
    }
}
