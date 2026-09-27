package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonStreamContextinRootTest {
    @Test
    public void testInRootWhenTypeIsRoot() throws Exception {
        JsonStreamContext context = new JsonStreamContext(0, 0) {
            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        assertTrue(context.inRoot());
    }

    @Test
    public void testInRootWhenTypeIsArray() throws Exception {
        JsonStreamContext context = new JsonStreamContext(1, 0) {
            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        assertFalse(context.inRoot());
    }

    @Test
    public void testInRootWhenTypeisObject() throws Exception {
        JsonStreamContext context = new JsonStreamContext(2, 0) {
            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        assertFalse(context.inRoot());
    }

    @Test
    public void testInRootWithBaseConstructor() throws Exception {
        JsonStreamContext base = new JsonStreamContext(0, 0) {
            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        JsonStreamContext context = new JsonStreamContext(base) {
            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        assertTrue(context.inRoot());
    }
}
