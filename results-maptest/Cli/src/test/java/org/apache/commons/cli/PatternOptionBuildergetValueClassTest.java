package org.apache.commons.cli;

import org.junit.Test;
import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PatternOptionBuildergetValueClassTest {

    @Test
    public void testGetValueClassForAtSymbol() {
        assertEquals(Object.class, PatternOptionBuilder.getValueClass('@'));
    }

    @Test
    public void testGetValueClassForColonSymbol() {
        assertEquals(String.class, PatternOptionBuilder.getValueClass(':'));
    }

    @Test
    public void testGetValueClassForPercentSymbol() {
        assertEquals(Number.class, PatternOptionBuilder.getValueClass('%'));
    }

    @Test
    public void testGetValueClassForPlusSymbol() {
        assertEquals(Class.class, PatternOptionBuilder.getValueClass('+'));
    }

    @Test
    public void testGetValueClassForHashSymbol() {
        assertEquals(Date.class, PatternOptionBuilder.getValueClass('#'));
    }

    @Test
    public void testGetValueClassForLessThanSymbol() {
        assertEquals(FileInputStream.class, PatternOptionBuilder.getValueClass('<'));
    }

    @Test
    public void testGetValueClassForGreaterThanSymbol() {
        assertEquals(File.class, PatternOptionBuilder.getValueClass('>'));
    }

    @Test
    public void testGetValueClassForStarSymbol() {
        assertEquals(File[].class, PatternOptionBuilder.getValueClass('*'));
    }

    @Test
    public void testGetValueClassForSlashSymbol() {
        assertEquals(URL.class, PatternOptionBuilder.getValueClass('/'));
    }

    @Test
    public void testGetValueClassForUnknownSymbol() {
        assertNull(PatternOptionBuilder.getValueClass('a'));
    }
}
