package com.zappos.json;

import java.util.List;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class JsonBeanIntrospectorgetMutatorsTest {
    private JsonBeanIntrospector introspector;
    private ZapposJson jacinda;

    @Before
    public void setUp() {
        jacinda = new ZapposJson(false);
        introspector = new JsonBeanIntrospector(jacinda);
    }

    @After
    public void tearDown() {
        introspector = null;
        jacinda = null;
    }

    @Test
    public void testGetMutators() {
        Class<?> clazz = String.class;
        List<JsonBeanAttribute> mutators = introspector.getMutators(clazz);
        Assert.assertNotNull(mutators);
    }
}
