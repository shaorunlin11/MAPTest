package org.apache.commons.cli;

import org.junit.Test;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class OptionsaddOption_5964f3dfTest {

    @Test
    public void testAddOption() throws Exception {
        // Create an Option instance
        Option option = new Option("a", "description");
        option.setLongOpt("long-a");
        option.setRequired(true);

        // Create an Options instance
        Options options = new Options();

        // Call the method under test
        Options result = options.addOption(option);

        // Verify that the method returns this
        assertSame(options, result);

        // Verify that the shortOpts map contains the option
        Map<String, Option> shortOpts = new LinkedHashMap<>();
        shortOpts.put("a", option);
        assertEquals(shortOpts, getShortOptions(options));

        // Verify that the longOpts map contains the option
        Map<String, Option> longOpts = new LinkedHashMap<>();
        longOpts.put("long-a", option);
        assertEquals(longOpts, getLongOptions(options));

        // Verify that the requiredOpts list contains the key
        List<Object> requiredOpts = new ArrayList<>();
        requiredOpts.add("a");
        assertEquals(requiredOpts, getRequiredOptions(options));
    }

    private Map<String, Option> getShortOptions(Options options) throws Exception {
        Field field = Options.class.getDeclaredField("shortOpts");
        field.setAccessible(true);
        return (Map<String, Option>) field.get(options);
    }

    private Map<String, Option> getLongOptions(Options options) throws Exception {
        Field field = Options.class.getDeclaredField("longOpts");
        field.setAccessible(true);
        return (Map<String, Option>) field.get(options);
    }

    private List<Object> getRequiredOptions(Options options) throws Exception {
        Field field = Options.class.getDeclaredField("requiredOpts");
        field.setAccessible(true);
        return (List<Object>) field.get(options);
    }

@Test
    public void testAddOptionWithRequiredOptsContainsKey() throws Exception {
        // Create an Option instance
        Option option = new Option("a", "description");
        option.setLongOpt("long-a");
        option.setRequired(true);

        // Create an Options instance
        Options options = new Options();

        // Set up requiredOpts to contain the key
        Field requiredOptsField = Options.class.getDeclaredField("requiredOpts");
        requiredOptsField.setAccessible(true);
        List<Object> requiredOpts = new ArrayList<>();
        requiredOpts.add("a");
        requiredOptsField.set(options, requiredOpts);

        // Call the method under test
        Options result = options.addOption(option);

        // Verify that the method returns this
        assertSame(options, result);

        // Verify that the requiredOpts list contains the key after addition
        List<Object> updatedRequiredOpts = (List<Object>) requiredOptsField.get(options);
        assertEquals(1, updatedRequiredOpts.size());
        assertEquals("a", updatedRequiredOpts.get(0));
    }
}
