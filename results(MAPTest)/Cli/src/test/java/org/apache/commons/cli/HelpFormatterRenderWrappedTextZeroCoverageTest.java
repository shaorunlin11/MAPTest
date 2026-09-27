package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.commons.cli.HelpFormatter;

public class HelpFormatterRenderWrappedTextZeroCoverageTest {
    @Test
    public void testRenderWrappedTextTargetLines() {
        HelpFormatter formatter = new HelpFormatter();

        // Create a text that will trigger pos == -1 during the first findWrapPos call
        // This text is designed to have no natural wrap position within the given width
        String text = "ThisIsAReallyLongStringWithoutAnySpacesThatShouldTriggerPosNegativeOne";
        int width = 20;

        // Set nextLineTabStop to a value less than width
        int nextLineTabStop = 15;

        // Create a StringBuffer to capture the output
        StringBuffer sb = new StringBuffer();

        // Call the method with parameters that meet the requirements
        formatter.renderWrappedText(sb, width, nextLineTabStop, text);

        // Verify that the output is not empty (basic coverage check)
        assertFalse(sb.toString().isEmpty());
    }
}
