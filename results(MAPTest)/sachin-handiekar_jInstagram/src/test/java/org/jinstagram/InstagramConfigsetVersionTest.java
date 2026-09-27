package org.jinstagram;

import org.jinstagram.model.Constants;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class InstagramConfigsetVersionTest {
    private InstagramConfig config;

    @Before
    public void setUp() {
        config = new InstagramConfig();
    }

    @Test
    public void testSetVersion() throws Exception {
        String expectedVersion = "1.2.3";
        String expectedApiUrl = String.format("%s/%s", Constants.BASE_URI, expectedVersion);

        config.setVersion(expectedVersion);

        Assert.assertEquals("Version should be set correctly", expectedVersion, config.getVersion());
        Assert.assertEquals("API URL should be updated with the new version", expectedApiUrl, config.getApiURL());
    }
}
