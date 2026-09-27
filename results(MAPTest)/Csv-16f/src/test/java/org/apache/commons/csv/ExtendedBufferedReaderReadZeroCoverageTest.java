package org.apache.commons.csv;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class ExtendedBufferedReaderReadZeroCoverageTest {
    @Test
    public void testReadWithCRAndLastCharNotCR() throws Exception {
        // Create a mock reader that returns CR (13) and then END_OF_STREAM (0)
        Reader mockReader = new Reader() {
            private boolean firstRead = true;
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                if (firstRead) {
                    firstRead = false;
                    return 13; // Return CR (13)
                } else {
                    return 0; // Return END_OF_STREAM (0)
                }
            }
            @Override
            public void close() throws IOException {
            }
        };

        ExtendedBufferedReader reader = new ExtendedBufferedReader(mockReader);

        // Use public method to set lastChar
        reader.read(); // First read to set lastChar
        reader.read(); // Second read to ensure lastChar is set

        // Call read() which should trigger the target line
        reader.read();
    }
}
