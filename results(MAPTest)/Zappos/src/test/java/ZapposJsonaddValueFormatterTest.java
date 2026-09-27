package com.zappos.json;

import org.junit.Test;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Date;
import java.time.LocalDate;
import java.time.Instant;
import com.zappos.json.format.BigDecimalFormatter;
import com.zappos.json.format.BigIntegerFormatter;
import com.zappos.json.format.JavaDateFormatter;
import com.zappos.json.format.JavaSqlDateFormatter;
import com.zappos.json.format.JavaTimeInstantFormatter;
import com.zappos.json.format.JavaTimeLocalDateFormatter;
import com.zappos.json.format.JavaTimestampFormatter;
import com.zappos.json.format.ValueFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ZapposJsonaddValueFormatterTest {
    @Test
    public void testAddValueFormatter() throws Exception {
        ZapposJson zapposJson = new ZapposJson();

        // Test adding formatters for different types
        zapposJson.addValueFormatter(Date.class, new JavaDateFormatter());
        zapposJson.addValueFormatter(java.sql.Date.class, new JavaSqlDateFormatter());
        zapposJson.addValueFormatter(java.sql.Timestamp.class, new JavaTimestampFormatter());
        zapposJson.addValueFormatter(java.math.BigInteger.class, new BigIntegerFormatter());
        zapposJson.addValueFormatter(java.math.BigDecimal.class, new BigDecimalFormatter());

        // Check if formatters are correctly stored
        Map<Class<?>, ValueFormatter<Object>> valueFormatters = zapposJson.VALUE_FORMATTERS;

        assertNotNull(valueFormatters.get(Date.class));
        assertNotNull(valueFormatters.get(java.sql.Date.class));
        assertNotNull(valueFormatters.get(java.sql.Timestamp.class));
        assertNotNull(valueFormatters.get(java.math.BigInteger.class));
        assertNotNull(valueFormatters.get(java.math.BigDecimal.class));

        // Test adding a formatter for a type that requires reflection check
        zapposJson.addValueFormatter(LocalDate.class, new JavaTimeLocalDateFormatter());
        zapposJson.addValueFormatter(Instant.class, new JavaTimeInstantFormatter());

        assertNotNull(valueFormatters.get(LocalDate.class));
        assertNotNull(valueFormatters.get(Instant.class));
    }
}
