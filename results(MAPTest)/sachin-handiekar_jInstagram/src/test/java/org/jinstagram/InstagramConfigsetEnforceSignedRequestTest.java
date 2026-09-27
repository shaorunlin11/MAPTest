package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class InstagramConfigsetEnforceSignedRequestTest {
    private InstagramConfig config;

    @Before
    public void setUp() {
        config = new InstagramConfig();
    }

    @Test
    public void testSetEnforceSignedRequest() throws Exception {
        boolean expectedValue = true;
        config.setEnforceSignedRequest(expectedValue);

        Field field = InstagramConfig.class.getDeclaredField("enforceSignedRequest");
        field.setAccessible(true);
        Boolean actualValue = (Boolean) field.get(config);

        assertEquals(expectedValue, actualValue);
    }
}
