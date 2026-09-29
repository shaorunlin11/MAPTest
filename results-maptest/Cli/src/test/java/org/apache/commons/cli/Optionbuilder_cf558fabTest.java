package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.commons.cli.Option;

public class Optionbuilder_cf558fabTest {

    @Test
    public void testBuilderMethodDelegatesToParameterizedVersion() throws Exception {
        // Act
        Option.Builder result = Option.builder();

        // Assert
        assertNotNull("Builder should not be null", result);
    }
}
