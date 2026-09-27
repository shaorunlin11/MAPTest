package com.zappos.json;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.zappos.json.JsonConfig.ReaderConfig;
import com.zappos.json.JsonConfig.WriterConfig;
import com.zappos.json.format.BigDecimalFormatter;
import com.zappos.json.format.BigIntegerFormatter;
import com.zappos.json.format.JavaDateFormatter;
import com.zappos.json.format.JavaSqlDateFormatter;
import com.zappos.json.format.JavaTimeInstantFormatter;
import com.zappos.json.format.JavaTimeLocalDateFormatter;
import com.zappos.json.format.JavaTimestampFormatter;
import com.zappos.json.format.ValueFormatter;
import com.zappos.json.util.Reflections;
import com.zappos.json.util.JsonUtils;
import com.zappos.json.util.Strings;
import com.zappos.json.JsonWriterCodeGenerator;
import com.zappos.json.JsonReaderCodeGenerator;
import com.zappos.json.JsonWriterInvoker;
import com.zappos.json.JsonReaderInvoker;

@RunWith(JUnit4.class)
public class ZapposJsonregister_27f48c32Test {
    private ZapposJson zapposJson;
    private Field writerCodeGeneratorField;
    private Field readerCodeGeneratorField;

    @Before
    public void setUp() throws Exception {
        zapposJson = new ZapposJson();
        writerCodeGeneratorField = ZapposJson.class.getDeclaredField("writerCodeGenerator");
        writerCodeGeneratorField.setAccessible(true);
        readerCodeGeneratorField = ZapposJson.class.getDeclaredField("readerCodeGenerator");
        readerCodeGeneratorField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        writerCodeGeneratorField.setAccessible(false);
        readerCodeGeneratorField.setAccessible(false);
    }

    @Test(timeout = 30000)
    public void testRegisterClassWithValidType() throws Exception {
        Class<?> clazz = String.class;

        zapposJson.register(clazz);

        Object writerCodeGenerator = writerCodeGeneratorField.get(zapposJson);
        Object readerCodeGenerator = readerCodeGeneratorField.get(zapposJson);

        Assert.assertNotNull("Writer code generator should not be null", writerCodeGenerator);
        Assert.assertNotNull("Reader code generator should not be null", readerCodeGenerator);
    }

    @Test(timeout = 30000)
    public void testRegisterClassWithExceptionInWriterRegistration() throws Exception {
        Class<?> clazz = String.class;

        // Mock writerCodeGenerator to throw exception
        JsonWriterCodeGenerator mockWriterGenerator = new JsonWriterCodeGenerator(zapposJson, new JsonBeanIntrospector(zapposJson)) {
            @Override
            protected JsonWriterInvoker registerWriter(Class<?> clazz) throws Exception {
                throw new Exception("Simulated registration failure");
            }
        };
        writerCodeGeneratorField.set(zapposJson, mockWriterGenerator);

        try {
            zapposJson.register(clazz);
            Assert.fail("Expected JsonException was not thrown");
        } catch (JsonException e) {
            Assert.assertTrue("Exception message should contain original exception message",
                    e.getMessage().contains("Simulated registration failure"));
        }
    }

    @Test(timeout = 30000)
    public void testRegisterClassWithExceptionInReaderRegistration() throws Exception {
        Class<?> clazz = String.class;

        // Mock readerCodeGenerator to throw exception
        JsonReaderCodeGenerator mockReaderGenerator = new JsonReaderCodeGenerator(zapposJson, new JsonBeanIntrospector(zapposJson)) {
            @Override
            protected JsonReaderInvoker registerReader(Class<?> clazz) throws Exception {
                throw new Exception("Simulated registration failure");
            }
        };
        readerCodeGeneratorField.set(zapposJson, mockReaderGenerator);

        try {
            zapposJson.register(clazz);
            Assert.fail("Expected JsonException was not thrown");
        } catch (JsonException e) {
            Assert.assertTrue("Exception message should contain original exception message",
                    e.getMessage().contains("Simulated registration failure"));
        }
    }
}
