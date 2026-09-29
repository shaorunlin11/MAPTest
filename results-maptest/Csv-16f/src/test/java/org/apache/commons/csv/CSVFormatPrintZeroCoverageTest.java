package org.apache.commons.csv;

import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;

public class CSVFormatPrintZeroCoverageTest {
    @Test
    public void testCSVFormatPrint() throws Exception {
        CSVFormat format = CSVFormat.DEFAULT;
        File out = new File("testfile.csv");
        Charset charset = Charset.defaultCharset();

        format.print(out, charset);
    }
}
