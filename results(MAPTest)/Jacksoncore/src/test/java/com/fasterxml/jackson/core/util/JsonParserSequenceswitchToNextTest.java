package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import com.fasterxml.jackson.core.JsonParser;

public class JsonParserSequenceswitchToNextTest {
    private JsonParserSequence sequence;
    private JsonParser[] parsers;

    @Before
    public void setUp() throws Exception {
        parsers = new JsonParser[2];
        parsers[0] = new JsonParserDelegate(parsers[0]) {
            @Override
            public boolean hasCurrentToken() {
                return false;
            }
        };
        parsers[1] = new JsonParserDelegate(parsers[1]) {
            @Override
            public boolean hasCurrentToken() {
                return false;
            }
        };
        sequence = new JsonParserSequence(false, parsers);
    }

    @Test
    public void testSwitchToNextWhenParsersExist() throws Exception {
        boolean result = sequence.switchToNext();
        Assert.assertTrue(result);
        Assert.assertEquals(2, sequence._nextParserIndex);
        Assert.assertEquals(parsers[1], sequence.delegate);
    }

    @Test
    public void testSwitchToNextWhenNoMoreParsers() throws Exception {
        sequence._nextParserIndex = 2;
        boolean result = sequence.switchToNext();
        Assert.assertFalse(result);
        Assert.assertEquals(2, sequence._nextParserIndex);
        Assert.assertEquals(parsers[0], sequence.delegate);
    }
}
