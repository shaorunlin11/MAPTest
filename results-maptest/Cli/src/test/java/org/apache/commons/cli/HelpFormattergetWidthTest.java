package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;

public class HelpFormattergetWidthTest {
    @Test
    public void testGetWidth() throws Exception {
        HelpFormatter formatter = new HelpFormatter();
        int width = formatter.getWidth();
        Assert.assertEquals(HelpFormatter.DEFAULT_WIDTH, width);
    }
}
