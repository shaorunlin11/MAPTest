package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionBuilderwithType_c14ba5ffTest {

    @Test
    public void testWithtype() {
        // Since the method is deprecated and simply delegates to another method,
        // we can verify that it returns an instance of OptionBuilder
        OptionBuilder result = OptionBuilder.withType(String.class);
        assertNotNull("The withType method should return a non-null OptionBuilder instance", result);
    }
}
