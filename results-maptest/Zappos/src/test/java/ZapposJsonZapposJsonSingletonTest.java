package com.zappos.json;

import org.junit.Test;
import org.junit.Assert;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
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

public class ZapposJsonZapposJsonSingletonTest {

    @Test
    public void testSingletonInstance() {
        ZapposJson.ZapposJsonSingleton instance1 = ZapposJson.ZapposJsonSingleton.INSTANCE;
        ZapposJson.ZapposJsonSingleton instance2 = ZapposJson.ZapposJsonSingleton.INSTANCE;

        Assert.assertSame("Singleton instance should be the same on multiple calls", instance1, instance2);

        ZapposJson zapposJson1 = instance1.get();
        ZapposJson zapposJson2 = instance2.get();

        Assert.assertSame("get() should return the same ZapposJson instance", zapposJson1, zapposJson2);
    }

    @Test
    public void testZapposJsonInitialization() throws Exception {
        ZapposJson.ZapposJsonSingleton instance = ZapposJson.ZapposJsonSingleton.INSTANCE;
        ZapposJson zapposJson = instance.get();

        // Check that value formatters are properly registered
        Map<Class<?>, ValueFormatter<Object>> valueFormatters = (Map<Class<?>, ValueFormatter<Object>>) getPrivateField(zapposJson, "VALUE_FORMATTERS");

        Assert.assertTrue("Should have Date formatter", valueFormatters.containsKey(Date.class));
        Assert.assertTrue("Should have java.sql.Date formatter", valueFormatters.containsKey(java.sql.Date.class));
        Assert.assertTrue("Should have java.sql.Timestamp formatter", valueFormatters.containsKey(java.sql.Timestamp.class));
        Assert.assertTrue("Should have java.math.BigInteger formatter", valueFormatters.containsKey(java.math.BigInteger.class));
        Assert.assertTrue("Should have java.math.BigDecimal formatter", valueFormatters.containsKey(java.math.BigDecimal.class));

        if (Reflections.classPresent("java.time.chrono.ChronoLocalDate")) {
            Assert.assertTrue("Should have LocalDate formatter", valueFormatters.containsKey(LocalDate.class));
            Assert.assertTrue("Should have Instant formatter", valueFormatters.containsKey(Instant.class));
        }

        // Check that introspector and code generators are initialized
        JsonBeanIntrospector introspector = (JsonBeanIntrospector) getPrivateField(zapposJson, "jsonBeanIntrospector");
        Assert.assertNotNull("JsonBeanIntrospector should be initialized", introspector);

        JsonWriterCodeGenerator writerGenerator = (JsonWriterCodeGenerator) getPrivateField(zapposJson, "writerCodeGenerator");
        Assert.assertNotNull("JsonWriterCodeGenerator should be initialized", writerGenerator);

        JsonReaderCodeGenerator readerGenerator = (JsonReaderCodeGenerator) getPrivateField(zapposJson, "readerCodeGenerator");
        Assert.assertNotNull("JsonReaderCodeGenerator should be initialized", readerGenerator);
    }

    private Object getPrivateField(Object obj, String fieldName) throws Exception {
        java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}
