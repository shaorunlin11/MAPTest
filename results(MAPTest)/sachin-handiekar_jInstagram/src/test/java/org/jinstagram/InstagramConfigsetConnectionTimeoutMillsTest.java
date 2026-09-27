package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class InstagramConfigsetConnectionTimeoutMillsTest {
    private InstagramConfig config;

    @Before
    public void setUp() {
        config = new InstagramConfig();
    }

    @Test
    public void testSetConnectionTimeoutMills() throws Exception {
        int expectedTimeout = 5000;
        config.setConnectionTimeoutMills(expectedTimeout);

        // Use reflection to verify the field value
        java.lang.reflect.Field field = InstagramConfig.class.getDeclaredField("connectionTimeoutMills");
        field.setAccessible(true);
        Integer actualTimeout = (Integer) field.get(config);

        Assert.assertEquals("Connection timeout should be set correctly", expectedTimeout, actualTimeout.intValue());
    }
}
