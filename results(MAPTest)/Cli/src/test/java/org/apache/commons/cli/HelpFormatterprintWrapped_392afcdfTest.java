package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.io.PrintWriter;
import java.io.StringWriter;

public class HelpFormatterprintWrapped_392afcdfTest {
    private HelpFormatter formatter;
    private StringWriter stringWriter;
    private PrintWriter printWriter;

    @Before
    public void setUp() {
        formatter = new HelpFormatter();
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
    }

    @After
    public void tearDown() {
        try {
            if (printWriter != null) {
                printWriter.close();
            }
            if (stringWriter != null) {
                stringWriter.close();
            }
        } catch (Exception e) {
            // Ignore
        }
    }

    @Test
    public void testPrintWrapped() throws Exception {
        String testText = "This is a test text that should be printed wrapped.";
        int width = 20;

        formatter.printWrapped(printWriter, width, testText);

        String result = stringWriter.toString();
        Assert.assertTrue("Expected wrapped text but got: " + result, result.contains("This is a test text"));
    }
}
