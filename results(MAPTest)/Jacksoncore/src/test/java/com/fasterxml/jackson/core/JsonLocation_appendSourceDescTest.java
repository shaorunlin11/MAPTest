package com.fasterxml.jackson.core;
import org.junit.Test;
import static org.junit.Assert.*;
public class JsonLocation_appendSourceDescTest {
    @Test
    public void testAppendSourceDesc_NullSource() {
        JsonLocation location = new JsonLocation(null, -1L, -1L, -1, -1);
        StringBuilder sb = new StringBuilder();
        location._appendSourceDesc(sb);
        assertEquals("UNKNOWN", sb.toString());
    }

    @Test
    public void testAppendSourceDesc_ClassType() {
        JsonLocation location = new JsonLocation(String.class, -1L, -1L, -1, -1);
        StringBuilder sb = new StringBuilder();
        location._appendSourceDesc(sb);
        assertEquals("(String)", sb.toString());
    }

    @Test
    public void testAppendSourceDesc_SimpleClassName() {
        JsonLocation location = new JsonLocation(java.util.ArrayList.class, -1L, -1L, -1, -1);
        StringBuilder sb = new StringBuilder();
        location._appendSourceDesc(sb);
        assertEquals("(ArrayList)", sb.toString());
    }

@Test
    public void testAppendSourceDesc_CustomObjectWithNonJavaName() {
        Object customObject = new Object();
        JsonLocation location = new JsonLocation(customObject, -1L, -1L, -1, -1);
        StringBuilder sb = new StringBuilder();
        location._appendSourceDesc(sb);
        assertEquals("(Object)", sb.toString());
    }
}
