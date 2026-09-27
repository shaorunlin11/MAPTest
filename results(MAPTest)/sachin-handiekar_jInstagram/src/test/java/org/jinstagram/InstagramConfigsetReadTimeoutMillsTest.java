package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class InstagramConfigsetReadTimeoutMillsTest {
    private InstagramConfig config;

    @Before
    public void setUp() {
        config = new InstagramConfig();
    }

    @Test
    public void testSetReadTimeoutMills() throws Exception {
        int expectedTimeout = 5000;
        config.setReadTimeoutMills(expectedTimeout);

        // Use reflection to verify the field value
        java.lang.reflect.Field field = InstagramConfig.class.getDeclaredField("readTimeoutMills");
        field.setAccessible(true);
        int actualTimeout = (Integer) field.get(config);

        Assert.assertEquals(expectedTimeout, actualTimeout);
    }
}
