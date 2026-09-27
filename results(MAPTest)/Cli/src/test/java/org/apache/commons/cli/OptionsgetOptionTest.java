package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.LinkedHashMap;

public class OptionsgetOptionTest {
    @Test
    public void testGetOptionWithShortOption() {
        Options options = new Options();
        Option option = new Option("a", "test");
        options.addOption(option);

        Option result = options.getOption("-a");
        assertEquals(option, result);
    }

    @Test
    public void testGetOptionWithLongOption() {
        Options options = new Options();
        Option option = new Option("test", "description");
        options.addOption(option);

        Option result = options.getOption("--test");
        assertEquals(option, result);
    }

    @Test
    public void testGetOptionWithNoHyphens() {
        Options options = new Options();
        Option option = new Option("b", "test");
        options.addOption(option);

        Option result = options.getOption("b");
        assertEquals(option, result);
    }

    @Test
    public void testGetOptionWithLeadingHyphens() {
        Options options = new Options();
        Option option = new Option("c", "test");
        options.addOption(option);

        Option result = options.getOption("-c");
        assertEquals(option, result);
    }

    @Test
    public void testGetOptionWithNonExistentOption() {
        Options options = new Options();

        Option result = options.getOption("nonexistent");
        assertNull(result);
    }

    @Test
    public void testGetOptionWithShortOptionButNotPresent() {
        Options options = new Options();

        Option result = options.getOption("x");
        assertNull(result);
    }

    @Test
    public void testGetOptionWithLongOptionButNotPresent() {
        Options options = new Options();

        Option result = options.getOption("--long");
        assertNull(result);
    }
}
