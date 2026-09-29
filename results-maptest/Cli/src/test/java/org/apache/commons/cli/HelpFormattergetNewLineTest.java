package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;

public class HelpFormattergetNewLineTest {
    @Test
    public void testGetNewLine() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        String expectedNewLine = System.getProperty("line.separator");
        String actualNewLine = formatter.getNewLine();
        Assert.assertEquals(expectedNewLine, actualNewLine);
    }
}
