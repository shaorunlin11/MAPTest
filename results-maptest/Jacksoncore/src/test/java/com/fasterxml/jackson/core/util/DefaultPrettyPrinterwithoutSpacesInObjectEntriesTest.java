package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultPrettyPrinterwithoutSpacesInObjectEntriesTest {

    @Test
    public void testWithoutSpacesInObjectEntries() throws Exception {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        DefaultPrettyPrinter newPrinter = printer.withoutSpacesInObjectEntries();

        assertFalse("should have spacesInObjectEntries set to false", newPrinter._spacesInObjectEntries);
    }
}
