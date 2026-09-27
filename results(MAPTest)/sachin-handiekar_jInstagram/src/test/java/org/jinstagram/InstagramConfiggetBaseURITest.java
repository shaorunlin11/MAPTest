package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.jinstagram.model.Constants;

public class InstagramConfiggetBaseURITest {
    private InstagramConfig config;

    @Before
    public void setUp() {
        config = new InstagramConfig();
    }

    @Test
    public void testGetBaseURI_returnsInitializedValue() {
        String expectedBaseURI = Constants.BASE_URI;
        Assert.assertEquals(expectedBaseURI, config.getBaseURI());
    }
}
