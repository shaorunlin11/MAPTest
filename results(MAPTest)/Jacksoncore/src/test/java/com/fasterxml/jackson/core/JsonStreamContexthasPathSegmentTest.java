package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class JsonStreamContexthasPathSegmentTest {
    private JsonStreamContext context;

    @Before
    public void setUp() {
        context = new JsonStreamContext(0, 0) {
            @Override
            public boolean hasCurrentIndex() {
                return false;
            }

            @Override
            public boolean hasCurrentName() {
                return false;
            }

            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
    }

    @After
    public void tearDown() {
        context = null;
    }

    @Test
    public void testHasPathSegmentForObjectType() {
        context = new JsonStreamContext(2, 0) {
            @Override
            public boolean hasCurrentIndex() {
                return false;
            }

            @Override
            public boolean hasCurrentName() {
                return true;
            }

            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        Assert.assertTrue(context.hasPathSegment());
    }

    @Test
    public void testHasPathSegmentForArrayType() {
        context = new JsonStreamContext(1, 0) {
            @Override
            public boolean hasCurrentIndex() {
                return true;
            }

            @Override
            public boolean hasCurrentName() {
                return false;
            }

            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        Assert.assertTrue(context.hasPathSegment());
    }

    @Test
    public void testHasPathSegmentForOtherType() {
        context = new JsonStreamContext(0, 0) {
            @Override
            public boolean hasCurrentIndex() {
                return false;
            }

            @Override
            public boolean hasCurrentName() {
                return false;
            }

            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        Assert.assertFalse(context.hasPathSegment());
    }

    @Test
    public void testHasPathSegmentForObjectWithNoCurrentName() {
        context = new JsonStreamContext(2, 0) {
            @Override
            public boolean hasCurrentIndex() {
                return false;
            }

            @Override
            public boolean hasCurrentName() {
                return false;
            }

            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        Assert.assertFalse(context.hasPathSegment());
    }

    @Test
    public void testHasPathSegmentForArrayWithNoCurrentIndex() {
        context = new JsonStreamContext(1, 0) {
            @Override
            public boolean hasCurrentIndex() {
                return false;
            }

            @Override
            public boolean hasCurrentName() {
                return false;
            }

            @Override
            public String getCurrentName() {
                return null;
            }

            @Override
            public JsonStreamContext getParent() {
                return null;
            }
        };
        Assert.assertFalse(context.hasPathSegment());
    }
}
