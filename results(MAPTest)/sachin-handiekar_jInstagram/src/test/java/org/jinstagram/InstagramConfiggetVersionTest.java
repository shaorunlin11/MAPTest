package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class InstagramConfiggetVersionTest {
    private InstagramConfig config;

    @Before
    public void setUp() {
        config = new InstagramConfig();
    }

    @Test
    public void testGetVersionReturnsInitializedValue() throws Exception {
        String expectedVersion = org.jinstagram.model.Constants.VERSION;
        String actualVersion = config.getVersion();
        assertEquals("The getVersion method should return the initialized version value", expectedVersion, actualVersion);
    }
}
