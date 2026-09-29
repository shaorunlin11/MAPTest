package com.zappos.json;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import com.zappos.json.JsonReaderCodeGenerator;
import com.zappos.json.JsonWriterCodeGenerator;
import java.util.Date;
import java.lang.reflect.Method;

public class ZapposJsonderegisterTest {
    private ZapposJson zapposJson;
    private JsonWriterCodeGenerator writerCodeGenerator;
    private JsonReaderCodeGenerator readerCodeGenerator;

    @Before
    public void setUp() throws Exception {
        zapposJson = new ZapposJson();
        Field writerField = ZapposJson.class.getDeclaredField("writerCodeGenerator");
        writerField.setAccessible(true);
        writerCodeGenerator = (JsonWriterCodeGenerator) writerField.get(zapposJson);

        Field readerField = ZapposJson.class.getDeclaredField("readerCodeGenerator");
        readerField.setAccessible(true);
        readerCodeGenerator = (JsonReaderCodeGenerator) readerField.get(zapposJson);
    }

    @After
    public void tearDown() throws Exception {
        zapposJson = null;
        writerCodeGenerator = null;
        readerCodeGenerator = null;
    }

    @Test
    public void testDeregisterCallsWriterAndReaderGenerators() throws Exception {
        Class<?> testClass = Date.class;

        // Capture original methods
        Method writerMethod = JsonWriterCodeGenerator.class.getDeclaredMethod("deregister", Class.class);
        writerMethod.setAccessible(true);

        Method readerMethod = JsonReaderCodeGenerator.class.getDeclaredMethod("deregister", Class.class);
        readerMethod.setAccessible(true);

        // Create mocks to verify method calls
        final boolean[] writerCalled = {false};
        final boolean[] readerCalled = {false};

        // Replace real generators with mocks
        Field writerField = ZapposJson.class.getDeclaredField("writerCodeGenerator");
        writerField.setAccessible(true);
        writerField.set(zapposJson, new JsonWriterCodeGenerator(null, null) {
            @Override
            protected void deregister(Class<?> clazz) {
                writerCalled[0] = true;
            }
        });

        Field readerField = ZapposJson.class.getDeclaredField("readerCodeGenerator");
        readerField.setAccessible(true);
        readerField.set(zapposJson, new JsonReaderCodeGenerator(null, null) {
            @Override
            protected void deregister(Class<?> clazz) {
                readerCalled[0] = true;
            }
        });

        // Execute method under test
        zapposJson.deregister(testClass);

        // Verify both generators were called
        Assert.assertTrue("Writer code generator should be called", writerCalled[0]);
        Assert.assertTrue("Reader code generator should be called", readerCalled[0]);
    }
}
