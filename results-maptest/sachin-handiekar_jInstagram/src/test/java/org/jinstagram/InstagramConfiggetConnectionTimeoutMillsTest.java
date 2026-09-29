package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.lang.reflect.Field;


public class InstagramConfiggetConnectionTimeoutMillsTest {
    private InstagramConfig config;

    @Before
    public void setUp() {
        config = new InstagramConfig();
    }

    @Test
    public void testGetConnectionTimeoutMills_returnsDefaultValueWhenNotSet() {
        int timeout = config.getConnectionTimeoutMills();
        Assert.assertEquals(0, timeout);
    }

    @Test
    public void testGetConnectionTimeoutMills_returnsSetValues() throws Exception {
        Field field = InstagramConfig.class.getDeclaredField("connectionTimeoutMills");
        field.setAccessible(true);
        field.setInt(config, 5000);

        int timeout = config.getConnectionTimeoutMills();
        Assert.assertEquals(5000, timeout);
    }
}
