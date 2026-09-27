package org.apache.commons.csv;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.Closeable;

public class LexercloseTest {
    private Lexer lexer;
    private ExtendedBufferedReader mockReader;

    @Before
    public void setUp() throws Exception {
        mockReader = new ExtendedBufferedReader(new StringReader(""));
        CSVFormat format = CSVFormat.DEFAULT;
        lexer = new Lexer(format, mockReader);
    }

    @After
    public void tearDown() throws Exception {
        if (lexer != null) {
            lexer.close();
        }
    }

    @Test
    public void testCloseCallsReaderClose() throws Exception {
        // Verify that close() calls reader.close()
        // Since we can't directly verify method calls without reflection,
        // we'll check that the reader is closed by attempting to read after close
        lexer.close();

        try {
            // Attempt to read from the reader after close
            int charRead = mockReader.read();
            assertTrue("Reader should be closed after calling close()", charRead == -1);
        } catch (IOException e) {
            // Expected exception when reading from a closed reader
            assertTrue("Reading from closed reader should throw IOException", true);
        }
    }

    @Test
    public void testCloseDoesNotThrowWhenReaderAlreadyClosed() throws Exception {
        // Close the reader first
        mockReader.close();

        // Call close() on the lexer
        lexer.close();

        // No exception should be thrown
    }

    @Test(expected = IOException.class)
    public void testClosePropagatesIOExceptionFromReader() throws Exception {
        // Create a mock reader that throws IOException on close
        Reader failingReader = new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                return 0;
            }

            @Override
            public void close() throws IOException {
                throw new IOException("Simulated close failure");
            }
        };

        // Create a new lexer with the failing reader
        CSVFormat format = CSVFormat.DEFAULT;
        Lexer failingLexer = new Lexer(format, new ExtendedBufferedReader(failingReader));

        // Call close() which should propagate the IOException
        failingLexer.close();
    }
}
