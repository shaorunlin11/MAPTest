package org.apache.commons.cli;

import org.junit.Test;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class OptionsaddOption_5cdee074Test {

    @Test
    public void testAddOption() throws Exception {
        Options options = new Options();
        String opt = "v";
        String longOpt = "version";
        boolean hasArg = false;
        String description = "display version information";

        Options result = options.addOption(opt, longOpt, hasArg, description);

        assertEquals(options, result);

        Map<String, Option> shortOpts = getShortOpts(options);
        Map<String, Option> longOpts = getLongOpts(options);

        assertNotNull(shortOpts.get(opt));
        assertNotNull(longOpts.get(longOpt));

        Option option = shortOpts.get(opt);
        assertEquals(opt, option.getOpt());
        assertEquals(longOpt, option.getLongOpt());
        assertEquals(hasArg, option.hasArg());
        assertEquals(description, option.getDescription());
    }

    private Map<String, Option> getShortOpts(Options options) throws Exception {
        Field field = Options.class.getDeclaredField("shortOpts");
        field.setAccessible(true);
        return (Map<String, Option>) field.get(options);
    }

    private Map<String, Option> getLongOpts(Options options) throws Exception {
        Field field = Options.class.getDeclaredField("longOpts");
        field.setAccessible(true);
        return (Map<String, Option>) field.get(options);
    }
}
