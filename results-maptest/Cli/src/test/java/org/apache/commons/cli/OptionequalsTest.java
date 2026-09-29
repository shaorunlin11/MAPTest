package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionequalsTest {

    @Test
    public void testEquals_SameInstance() {
        Option option = new Option("a", "description");
        assertTrue(option.equals(option));
    }

    @Test
    public void testEquals_NullObject() {
        Option option = new Option("a", "description");
        assertFalse(option.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        Option option = new Option("a", "description");
        Object other = new Object();
        assertFalse(option.equals(other));
    }

    @Test
    public void testEquals_SameOptAndLongOpt() {
        Option option1 = new Option("a", "long", false, "description");
        Option option2 = new Option("a", "long", false, "description");
        assertTrue(option1.equals(option2));
    }

    @Test
    public void testEquals_DifferentOpt() {
        Option option1 = new Option("a", "long", false, "description");
        Option option2 = new Option("b", "long", false, "description");
        assertFalse(option1.equals(option2));
    }

    @Test
    public void testEquals_DifferentLongOpt() {
        Option option1 = new Option("a", "long1", false, "description");
        Option option2 = new Option("a", "long2", false, "description");
        assertFalse(option1.equals(option2));
    }

    @Test
    public void testEquals_NullOpt() {
        Option option1 = new Option((String) null, "long", false, "description");
        Option option2 = new Option((String) null, "long", false, "description");
        assertTrue(option1.equals(option2));
    }

    @Test
    public void testEquals_NullLongOpt() {
        Option option1 = new Option("a", (String) null, false, "description");
        Option option2 = new Option("a", (String) null, false, "description");
        assertTrue(option1.equals(option2));
    }

    @Test
    public void testEquals_OptNullVsNonNull() {
        Option option1 = new Option((String) null, "long", false, "description");
        Option option2 = new Option("a", "long", false, "description");
        assertFalse(option1.equals(option2));
    }

    @Test
    public void testEquals_LongOptNullVsNonNull() {
        Option option1 = new Option("a", (String) null, false, "description");
        Option option2 = new Option("a", "long", false, "description");
        assertFalse(option1.equals(option2));
    }
}
