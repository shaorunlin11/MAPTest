package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionsetDescriptionTest {
    @Test
    public void testSetDescription() throws Exception {
        Option option = new Option("a", "description");
        assertEquals("description", option.getDescription());

        option.setDescription("new description");
        assertEquals("new description", option.getDescription());
    }

    @Test
    public void testSetDescriptionWithNull() throws Exception {
        Option option = new Option("a", "description");
        assertEquals("description", option.getDescription());

        option.setDescription(null);
        assertNull(option.getDescription());
    }
}
