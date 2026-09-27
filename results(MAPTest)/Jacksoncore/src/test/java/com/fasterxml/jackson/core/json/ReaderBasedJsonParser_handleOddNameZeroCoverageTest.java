package com.fasterxml.jackson.core.json;

import org.junit.Test;
import java.io.Reader;
import java.io.StringReader;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;

public class ReaderBasedJsonParser_handleOddNameZeroCoverageTest {
    @Test
    public void testHandleOddNameWithUnquotedNamesDisabled() throws Exception {
        // Create a reader with input that would trigger the target line
        String input = "abc";
        Reader reader = new StringReader(input);

        // Create a JsonFactory and parser
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(reader);

        // Cast to ReaderBasedJsonParser to access protected methods
        ReaderBasedJsonParser parserImpl = (ReaderBasedJsonParser) parser;

        // Set up the required object state using public API
        parserImpl.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);

        // Call the method under test
        parserImpl._handleOddName('a');
    }
}
