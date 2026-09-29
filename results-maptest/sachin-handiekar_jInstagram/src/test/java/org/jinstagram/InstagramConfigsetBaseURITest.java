package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import java.lang.reflect.Field;

public class InstagramConfigsetBaseURITest {
    private InstagramConfig config;

    @Before
    public void setUp() {
        config = new InstagramConfig();
    }

    @Test
    public void testSetBaseURI() throws Exception {
        String newBaseURI = "https://api.newinstagram.com";
        config.setBaseURI(newBaseURI);

        // Use reflection to access private fields
        Field baseURIField = InstagramConfig.class.getDeclaredField("baseURI");
        baseURIField.setAccessible(true);
        Assert.assertEquals("Base URI should be set correctly", newBaseURI, baseURIField.get(config));

        Field versionField = InstagramConfig.class.getDeclaredField("version");
        versionField.setAccessible(true);
        String version = (String) versionField.get(config);

        Field apiURLField = InstagramConfig.class.getDeclaredField("apiURL");
        apiURLField.setAccessible(true);
        Assert.assertEquals("API URL should be constructed correctly", String.format("%s/%s", newBaseURI, version), apiURLField.get(config));
    }
}
