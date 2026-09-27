package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.lang.reflect.Field;

public class OptionsaddRequiredOptionTest {

    @Test
    public void testAddRequiredOption() throws Exception {
        Options options = new Options();
        String opt = "v";
        String longOpt = "verbose";
        boolean hasArg = false;
        String description = "Enable verbose mode";

        // Use addRequiredOption instead of addOption
        Options result = options.addRequiredOption(opt, longOpt, hasArg, description);

        // Verify that the method returns 'this'
        assertSame(options, result);

        // Verify that the option was added to shortOpts
        Map<String, Option> shortOpts = getPrivateField(options, "shortOpts");
        assertTrue(shortOpts.containsKey(opt));
        assertEquals(opt, shortOpts.get(opt).getOpt());

        // Verify that the option was added to longOpts
        Map<String, Option> longOpts = getPrivateField(options, "longOpts");
        assertTrue(longOpts.containsKey(longOpt));
        assertEquals(longOpt, longOpts.get(longOpt).getLongOpt());

        // Verify that the option is marked as required
        Option option = shortOpts.get(opt);
        assertTrue(option.isRequired());

        // Verify that the option is added to requiredOpts
        List<String> requiredOpts = getPrivateField(options, "requiredOpts");
        assertTrue(requiredOpts.contains(opt));
    }

    private <T> T getPrivateField(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(obj);
    }
}
