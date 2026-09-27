package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;
import java.io.DataOutput;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class JsonFactorycreateGenerator_4617b7d7Test {

    @Test
    public void testCreateGeneratorWithValidDataOutput() throws IOException {
        JsonFactory factory = new JsonFactory();
        DataOutput out = new DataOutputStream(new ByteArrayOutputStream());

        JsonGenerator generator = factory.createGenerator(out);

        Assert.assertNotNull(generator);
    }
}
