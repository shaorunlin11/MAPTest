package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class InstagramConfigsetConnectionKeepAliveTest {
    private InstagramConfig config;

    @Before
    public void setUp() {
        config = new InstagramConfig();
    }

    @Test
    public void testSetConnectionKeepAliveWithTrue() throws Exception {
        config.setConnectionKeepAlive(true);
        Field field = InstagramConfig.class.getDeclaredField("connectionKeepAlive");
        field.setAccessible(true);
        Boolean result = (Boolean) field.get(config);
        assertTrue(result);
    }

    @Test
    public void testSetConnectionKeepAliveWithFalse() throws Exception {
        config.setConnectionKeepAlive(false);
        Field field = InstagramConfig.class.getDeclaredField("connectionKeepAlive");
        field.setAccessible(true);
        Boolean result = (Boolean) field.get(config);
        assertFalse(result);
    }
}
