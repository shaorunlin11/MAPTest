package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.lang.reflect.Field;


public class InstagramConfigisConnectionKeepAliveTest {
    private InstagramConfig config;

    @Before
    public void setUp() {
        config = new InstagramConfig();
    }

    @Test
    public void testIsConnectionKeepAlive_DefaultValue() {
        Assert.assertFalse(config.isConnectionKeepAlive());
    }

    @Test
    public void testIsConnectionKeepAlive_SetToTrue() throws Exception {
        Field field = InstagramConfig.class.getDeclaredField("connectionKeepAlive");
        field.setAccessible(true);
        field.set(config, true);
        Assert.assertTrue(config.isConnectionKeepAlive());
    }

    @Test
    public void testIsConnectionKeepAlive_SetToFalse() throws Exception {
        Field field = InstagramConfig.class.getDeclaredField("connectionKeepAlive");
        field.setAccessible(true);
        field.set(config, false);
        Assert.assertFalse(config.isConnectionKeepAlive());
    }
}
