package com.zappos.json;

import org.junit.Test;
import org.junit.Before;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.reflect.Field;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import com.zappos.json.format.ValueFormatter;
import com.zappos.json.util.Reflections;
import static org.junit.Assert.*;

public class ZapposJsonremoveValueFormatterTest {
    private ZapposJson zapposJson;

    @Before
    public void setUp() throws Exception {
        zapposJson = new ZapposJson();
    }

    @Test
    public void testRemoveValueFormatter_WhenObjectClassIsPresent_ShouldRemoveEntryFromMap() throws Exception {
        Class<?> dateClass = Date.class;
        Map<Class<?>, ValueFormatter<Object>> valueFormatters = getValueFormattersField(zapposJson);

        assertTrue("Date formatter should be present initially", valueFormatters.containsKey(dateClass));

        zapposJson.removeValueFormatter(dateClass);

        assertFalse("Date formatter should be removed", valueFormatters.containsKey(dateClass));
    }

    @Test
    public void testRemoveValueFormatter_WhenObjectClassIsNotPresent_ShouldNotThrowException() throws Exception {
        Class<?> unknownClass = String.class;
        zapposJson.removeValueFormatter(unknownClass);

        // No exception expected, method should simply do nothing
    }

    @Test
    public void testRemoveValueFormatter_WithJavaSqlDateClass_ShouldRemoveEntryFromMap() throws Exception {
        Class<?> sqlDateClass = java.sql.Date.class;
        Map<Class<?>, ValueFormatter<Object>> valueFormatters = getValueFormattersField(zapposJson);

        assertTrue("Java SQL Date formatter should be present initially", valueFormatters.containsKey(sqlDateClass));

        zapposJson.removeValueFormatter(sqlDateClass);

        assertFalse("Java SQL Date formatter should be removed", valueFormatters.containsKey(sqlDateClass));
    }

    @Test
    public void testRemoveValueFormatter_WithLocalDateClass_ShouldRemoveEntryFromMapIfPresent() throws Exception {
        Class<?> localDateClass = LocalDate.class;
        Map<Class<?>, ValueFormatter<Object>> valueFormatters = getValueFormattersField(zapposJson);

        if (Reflections.classPresent("java.time.chrono.ChronoLocalDate")) {
            assertTrue("LocalDate formatter should be present initially", valueFormatters.containsKey(localDateClass));

            zapposJson.removeValueFormatter(localDateClass);

            assertFalse("LocalDate formatter should be removed", valueFormatters.containsKey(localDateClass));
        } else {
            // If the class is not present, the method should not throw an exception
            zapposJson.removeValueFormatter(localDateClass);
        }
    }

    @Test
    public void testRemoveValueFormatter_WithInstantClass_ShouldRemoveEntryFromMapIfPresent() throws Exception {
        Class<?> instantClass = Instant.class;
        Map<Class<?>, ValueFormatter<Object>> valueFormatters = getValueFormattersField(zapposJson);

        if (Reflections.classPresent("java.time.chrono.ChronoLocalDate")) {
            assertTrue("Instant formatter should be present initially", valueFormatters.containsKey(instantClass));

            zapposJson.removeValueFormatter(instantClass);

            assertFalse("Instant formatter should be removed", valueFormatters.containsKey(instantClass));
        } else {
            // If the class is not present, the method should not throw an exception
            zapposJson.removeValueFormatter(instantClass);
        }
    }

    private Map<Class<?>, ValueFormatter<Object>> getValueFormattersField(ZapposJson instance) throws Exception {
        Field field = ZapposJson.class.getDeclaredField("VALUE_FORMATTERS");
        field.setAccessible(true);
        return (Map<Class<?>, ValueFormatter<Object>>) field.get(instance);
    }
}
