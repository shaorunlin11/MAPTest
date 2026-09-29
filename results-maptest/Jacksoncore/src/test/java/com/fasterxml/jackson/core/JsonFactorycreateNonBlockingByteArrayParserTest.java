package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;

public class JsonFactorycreateNonBlockingByteArrayParserTest {

    @Test
    public void testCreateNonBlockingByteArrayParser() throws IOException {
        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createNonBlockingByteArrayParser();
        Assert.assertNotNull(parser);
        Assert.assertTrue(parser instanceof JsonParser);
    }
}
