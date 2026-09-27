package org.apache.commons.csv;
import static org.apache.commons.csv.Constants.CR;
import static org.apache.commons.csv.Constants.LF;
import static org.apache.commons.csv.Constants.SP;
import org.junit.Test;
import org.junit.Assert;
import java.io.StringWriter;
import java.io.IOException;
public class CSVPrinterprintCommentTest {
    @Test
    public void testPrintCommentWithNoCommentMarker() throws IOException {
        CSVFormat format = CSVFormat.DEFAULT;
        StringWriter writer = new StringWriter();
        CSVPrinter printer = new CSVPrinter(writer, format);

        printer.printComment("This is a comment");

        // Since no comment marker is set, nothing should be written
        Assert.assertEquals("", writer.toString());
    }




}
