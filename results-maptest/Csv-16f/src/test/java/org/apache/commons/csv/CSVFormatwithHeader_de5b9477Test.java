package org.apache.commons.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.junit.Test;

public class CSVFormatwithHeader_de5b9477Test {

    @Test
    public void testWithHeader_NullEnum() throws Exception {
        CSVFormat format = CSVFormat.DEFAULT.withHeader((Class<? extends Enum<?>>) null);
        Field headerField = CSVFormat.class.getDeclaredField("header");
        headerField.setAccessible(true);
        String[] header = (String[]) headerField.get(format);
        assertNull("Header should be null when enum is null", header);
    }

    @Test
    public void testWithHeader_EnumValues() throws Exception {
        CSVFormat format = CSVFormat.DEFAULT.withHeader(TestEnum.class);
        Field headerField = CSVFormat.class.getDeclaredField("header");
        headerField.setAccessible(true);
        String[] header = (String[]) headerField.get(format);
        String[] expectedHeader = { "VALUE1", "VALUE2", "VALUE3" };
        assertEquals("Header should match enum values", Arrays.toString(expectedHeader), Arrays.toString(header));
    }

    @Test
    public void testWithHeader_EnumWithEmptyConstants() throws Exception {
        CSVFormat format = CSVFormat.DEFAULT.withHeader(EmptyEnum.class);
        Field headerField = CSVFormat.class.getDeclaredField("header");
        headerField.setAccessible(true);
        String[] header = (String[]) headerField.get(format);
        assertEquals("Header should be empty when enum has no constants", 0, header.length);
    }

    @Test
    public void testWithHeader_EnumWithMultipleConstants() throws Exception {
        CSVFormat format = CSVFormat.DEFAULT.withHeader(MultiEnum.class);
        Field headerField = CSVFormat.class.getDeclaredField("header");
        headerField.setAccessible(true);
        String[] header = (String[]) headerField.get(format);
        String[] expectedHeader = { "A", "B", "C", "D" };
        assertEquals("Header should match enum values", Arrays.toString(expectedHeader), Arrays.toString(header));
    }
}

enum TestEnum { VALUE1, VALUE2, VALUE3 }
enum EmptyEnum {}
enum MultiEnum { A, B, C, D }
