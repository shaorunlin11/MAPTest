package com.zappos.json;

import org.junit.Test;
import java.util.concurrent.ConcurrentHashMap;
import java.time.Instant;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import com.zappos.json.format.BigDecimalFormatter;
import com.zappos.json.format.BigIntegerFormatter;
import com.zappos.json.format.JavaDateFormatter;
import com.zappos.json.format.JavaSqlDateFormatter;
import com.zappos.json.format.JavaTimeInstantFormatter;
import com.zappos.json.format.JavaTimeLocalDateFormatter;
import com.zappos.json.format.JavaTimestampFormatter;
import com.zappos.json.format.ValueFormatter;
import com.zappos.json.util.Reflections;
import static org.junit.Assert.*;

public class ZapposJsongetValueFormatterTest {
    @Test
    public void testGetValueFormatter_returnsExpectedFormattersForKnownTypes() {
        ZapposJson zapposJson = new ZapposJson();

        ValueFormatter<?> dateFormatter = zapposJson.getValueFormatter(java.util.Date.class);
        assertTrue(dateFormatter instanceof JavaDateFormatter);

        ValueFormatter<?> sqlDateFormatter = zapposJson.getValueFormatter(java.sql.Date.class);
        assertTrue(sqlDateFormatter instanceof JavaSqlDateFormatter);

        ValueFormatter<?> timestampFormatter = zapposJson.getValueFormatter(java.sql.Timestamp.class);
        assertTrue(timestampFormatter instanceof JavaTimestampFormatter);

        ValueFormatter<?> bigIntegerFormatter = zapposJson.getValueFormatter(java.math.BigInteger.class);
        assertTrue(bigIntegerFormatter instanceof BigIntegerFormatter);

        ValueFormatter<?> bigDecimalFormatter = zapposJson.getValueFormatter(java.math.BigDecimal.class);
        assertTrue(bigDecimalFormatter instanceof BigDecimalFormatter);

        if (Reflections.classPresent("java.time.chrono.ChronoLocalDate")) {
            ValueFormatter<?> localDateFormatter = zapposJson.getValueFormatter(LocalDate.class);
            assertTrue(localDateFormatter instanceof JavaTimeLocalDateFormatter);

            ValueFormatter<?> instantFormatter = zapposJson.getValueFormatter(Instant.class);
            assertTrue(instantFormatter instanceof JavaTimeInstantFormatter);
        }
    }

    @Test
    public void testGetValueFormatter_returnsNullForUnknownType() {
        ZapposJson zapposJson = new ZapposJson();
        ValueFormatter<?> unknownFormatter = zapposJson.getValueFormatter(String.class);
        assertNull(unknownFormatter);
    }
}
