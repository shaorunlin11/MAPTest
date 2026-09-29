package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class InstagramConfigisEnforceSignedRequestTest {
    private InstagramConfig config;

    @Before
    public void setUp() {
        config = new InstagramConfig();
    }

    @After
    public void tearDown() {
        config = null;
    }

    @Test
    public void testIsEnforceSignedRequest_DefaultValue() {
        Assert.assertFalse(config.isEnforceSignedRequest());
    }

    @Test
    public void testIsEnforceSignedRequest_SetToTrue() throws Exception {
        Field field = InstagramConfig.class.getDeclaredField("enforceSignedRequest");
        field.setAccessible(true);
        field.set(config, true);
        Assert.assertTrue(config.isEnforceSignedRequest());
    }

    @Test
    public void testIsEnforceSignedRequest_SetToFalse() throws Exception {
        Field field = InstagramConfig.class.getDeclaredField("enforceSignedRequest");
        field.setAccessible(true);
        field.set(config, false);
        Assert.assertFalse(config.isEnforceSignedRequest());
    }
}
