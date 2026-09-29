package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonStreamContexttypeDescTest {

    @Test
    public void testTypeDescForRoot() throws Exception {
        JsonStreamContext context = new JsonStreamContext(0, 0) {
            @Override
            public String getCurrentName() {
                return "dummy";
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        assertEquals("root", context.typeDesc());
    }

    @Test
    public void testTypeDescForArray() throws Exception {
        JsonStreamContext context = new JsonStreamContext(1, 0) {
            @Override
            public String getCurrentName() {
                return "dummy";
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        assertEquals("Array", context.typeDesc());
    }

    @Test
    public void testTypeDescForObject() throws Exception {
        JsonStreamContext context = new JsonStreamContext(2, 0) {
            @Override
            public String getCurrentName() {
                return "dummy";
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        assertEquals("Object", context.typeDesc());
    }

    @Test
    public void testTypeDescForUnknownType() throws Exception {
        JsonStreamContext context = new JsonStreamContext(3, 0) {
            @Override
            public String getCurrentName() {
                return "dummy";
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        assertEquals("?", context.typeDesc());
    }
}
