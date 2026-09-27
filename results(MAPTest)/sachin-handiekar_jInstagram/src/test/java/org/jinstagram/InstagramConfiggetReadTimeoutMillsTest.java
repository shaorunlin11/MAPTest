package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class InstagramConfiggetReadTimeoutMillsTest {
    private InstagramConfig config;

    @Before
    public void setUp() {
        config = new InstagramConfig();
    }

    @Test
    public void testGetReadTimeoutMills_returnsDefaultValueWhenNotSet() {
        assertEquals(0, config.getReadTimeoutMills());
    }

    @Test
    public void testGetReadTimeoutMills_returnsSetReadTimeoutMills() throws Exception {
        Field readTimeoutMillsField = InstagramConfig.class.getDeclaredField("readTimeoutMills");
        readTimeoutMillsField.setAccessible(true);
        readTimeoutMillsField.setInt(config, 5000);

        assertEquals(5000, config.getReadTimeoutMills());
    }
}
