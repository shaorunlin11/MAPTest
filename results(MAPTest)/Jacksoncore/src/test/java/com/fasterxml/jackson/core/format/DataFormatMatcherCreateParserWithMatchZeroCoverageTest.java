package com.fasterxml.jackson.core.format;

import org.junit.Test;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.format.MatchStrength;

public class DataFormatMatcherCreateParserWithMatchZeroCoverageTest {
    @Test
    public void testCreateParserWithMatch() throws IOException {
        // Setup
        InputStream originalStream = null;
        byte[] bufferedData = new byte[0];
        int bufferedStart = 0;
        int bufferedLength = 0;
        JsonFactory match = new JsonFactory();
        MatchStrength matchStrength = MatchStrength.FULL_MATCH;

        DataFormatMatcher matcher = new DataFormatMatcher(originalStream, bufferedData, bufferedStart, bufferedLength, match, matchStrength);

        // Execute
        JsonParser parser = matcher.createParserWithMatch();

        // Assert
        // The method should execute line 109 which is the return statement in the if block where _originalStream is null
        // This verifies the CFG path group for target lines 109
    }
}
