package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

public class HelpFormatterprintOptionsTest {
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
    public void testPrintOptions() throws Exception {
        // Arrange
        Options options = new Options();
        List<Option> optionList = new ArrayList<>();
        Option option1 = new Option("a", "option-a", false, "Description for option a");
        Option option2 = new Option("b", "option-b", false, "Description for option b");
        optionList.add(option1);
        optionList.add(option2);
        options.addOption(option1);
        options.addOption(option2);

        // Act
        formatter.printOptions(printWriter, 80, options, 2, 3);

        // Assert
        String output = stringWriter.toString();
        Assert.assertTrue(output.contains(" -a,--option-a   Description for option a"));
        Assert.assertTrue(output.contains(" -b,--option-b   Description for option b"));
    }
}
