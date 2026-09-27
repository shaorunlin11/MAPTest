package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class OptionBuilderhasArgs_be8126ffTest {

    @Test
    public void testHasArgs() throws Exception {
        // Call the method under test
        OptionBuilder result = OptionBuilder.hasArgs();

        // Verify that the returned instance is not null
        assertNotNull("hasArgs should return a non-null instance", result);

        // Verify that numberOfArgs was set to Option.UNLIMITED_VALUES
        Field numberOfArgsField = OptionBuilder.class.getDeclaredField("numberOfArgs");
        numberOfArgsField.setAccessible(true);
        int actualNumberOfArgs = numberOfArgsField.getInt(OptionBuilder.class);
        assertEquals("numberOfArgs should be set to Option.UNLIMITED_VALUES", Option.UNLIMITED_VALUES, actualNumberOfArgs);
    }
}
