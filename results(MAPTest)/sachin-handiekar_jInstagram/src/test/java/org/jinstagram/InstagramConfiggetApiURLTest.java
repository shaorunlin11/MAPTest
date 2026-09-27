package org.jinstagram;

import org.jinstagram.model.Constants;
import org.junit.Test;
import static org.junit.Assert.*;

public class InstagramConfiggetApiURLTest {

    @Test
    public void testGetApiURL() throws Exception {
        InstagramConfig config = new InstagramConfig();
        String expectedApiUrl = Constants.API_URL;
        assertEquals("The API URL should match the constant value", expectedApiUrl, config.getApiURL());
    }
}
