package org.apache.commons.cli;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OptionsGetOptionGroupZeroCoverageTest {
    @Test
    public void testGetOptionGroup() {
        Options options = new Options();
        Map<String, OptionGroup> optionGroups = new HashMap<>();
        OptionGroup group = new OptionGroup();
        String key = "testKey";
        optionGroups.put(key, group);

        // Set the optionGroups field using reflection since it's private
        try {
            java.lang.reflect.Field field = Options.class.getDeclaredField("optionGroups");
            field.setAccessible(true);
            field.set(options, optionGroups);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Option opt = new Option(key, "testDescription");
        OptionGroup result = options.getOptionGroup(opt);
        assertEquals(group, result);
    }
}
