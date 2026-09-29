package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;

public class AlreadySelectedExceptiongetOptionGroupTest {

    @Test
    public void testGetOptionGroup() throws Exception {
        // Arrange
        OptionGroup group = new OptionGroup();
        Option option = new Option("a", "test");
        AlreadySelectedException exception = new AlreadySelectedException(group, option);

        // Act
        OptionGroup result = exception.getOptionGroup();

        // Assert
        Assert.assertEquals(group, result);
    }
}
