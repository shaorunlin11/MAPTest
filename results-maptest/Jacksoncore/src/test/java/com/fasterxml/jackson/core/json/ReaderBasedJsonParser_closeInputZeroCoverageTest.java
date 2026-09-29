package com.fasterxml.jackson.core.json;

import org.junit.Test;
import java.io.Reader;
import java.io.StringReader;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.util.BufferRecycler;

import com.fasterxml.jackson.core.base.ParserBase;


public class ReaderBasedJsonParser_closeInputZeroCoverageTest {
    @Test
    public void testCloseInputWithAutoCloseSourceEnabled() throws Exception {
        // Create a mock IOContext that returns true for isResourceManaged()
        IOContext ioContext = new IOContext(new BufferRecycler(), null, false) {
            @Override
            public boolean isResourceManaged() {
                return true;
            }
        };

        // Create a Reader
        Reader reader = new StringReader("{}");

        // Create a JsonFactory and parse a JSON string to get a ReaderBasedJsonParser
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(reader);

        // Cast to ReaderBasedJsonParser
        ReaderBasedJsonParser readerBasedJsonParser = (ReaderBasedJsonParser) parser;

        // Set the _ioContext field using reflection (allowed as it's a protected field)
        java.lang.reflect.Field ioContextField = ParserBase.class.getDeclaredField("_ioContext");
        ioContextField.setAccessible(true);
        ioContextField.set(readerBasedJsonParser, ioContext);

        // Set the _reader field using reflection (allowed as it's a protected field)
        java.lang.reflect.Field readerField = ReaderBasedJsonParser.class.getDeclaredField("_reader");
        readerField.setAccessible(true);
        readerField.set(readerBasedJsonParser, reader);

        // Enable AUTO_CLOSE_SOURCE feature
        readerBasedJsonParser.enable(Feature.AUTO_CLOSE_SOURCE);

        // Call the method under test
        readerBasedJsonParser._closeInput();

        // Verify that _reader is null after closing
        assert readerBasedJsonParser._reader == null;
    }
}
