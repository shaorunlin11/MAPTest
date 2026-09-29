package com.fasterxml.jackson.core.io;

import org.junit.Test;

public class JsonStringEncoderQuoteAsStringZeroCoverage_803Test {
    @Test
    public void testQuoteAsStringWithEscapableCharacter() {
        JsonStringEncoder encoder = new JsonStringEncoder();
        StringBuilder output = new StringBuilder();
        CharSequence input = "\u001F"; // Character that requires escaping

        encoder.quoteAsString(input, output);
    }

@Test
    public void testQuoteAsStringLine162() {
        JsonStringEncoder encoder = new JsonStringEncoder();
        StringBuilder output = new StringBuilder();
        CharSequence input = "abc\u001Fdef"; // Contains an escapable character

        encoder.quoteAsString(input, output);
    }
}
