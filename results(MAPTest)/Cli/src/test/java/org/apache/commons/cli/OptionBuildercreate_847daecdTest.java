package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionBuildercreate_847daecdTest {

    @Test
    public void testCreateWithChar() throws IllegalArgumentException {
        // Act
        Option result = OptionBuilder.create('t');

        // Assert
        assertNotNull("Result should not be null", result);
    }
}
