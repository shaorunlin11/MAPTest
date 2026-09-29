package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class InstagramConfigisRetryOnServerErrorTest {
    private InstagramConfig config;

    @Before
    public void setUp() {
        config = new InstagramConfig();
    }

    @Test
    public void testIsRetryOnServerError_returnsFalseByDefault() {
        assertFalse(config.isRetryOnServerError());
    }

    @Test
    public void testIsRetryOnServerError_returnsTrueWhenSet() throws Exception {
        Field field = InstagramConfig.class.getDeclaredField("retryOnServerError");
        field.setAccessible(true);
        field.set(config, true);
        assertTrue(config.isRetryOnServerError());
    }
}
