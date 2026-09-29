package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class OptiongetIdTest {
    private Option option;

    @Before
    public void setUp() throws Exception {
        option = new Option("a", "description");
    }

    @After
    public void tearDown() throws Exception {
        option = null;
    }

    @Test
    public void testGetId() throws Exception {
        // Arrange
        String expectedKey = "a";

        // Act
        int id = option.getId();

        // Assert
        assertEquals((int) expectedKey.charAt(0), id);
    }
}
