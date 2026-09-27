package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class ImagesgetStandardResolutionTest {
    private Images images;
    private ImageData standardResolutionMock;

    @Before
    public void setUp() {
        images = new Images();
        standardResolutionMock = new ImageData();
        // Use reflection to set the private field
        try {
            java.lang.reflect.Field field = Images.class.getDeclaredField("standardResolution");
            field.setAccessible(true);
            field.set(images, standardResolutionMock);
        } catch (Exception e) {
            Assert.fail("Failed to set up test: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        images = null;
        standardResolutionMock = null;
    }

    @Test
    public void testGetStandardResolutionReturnsExpectedValue() {
        ImageData result = images.getStandardResolution();
        Assert.assertNotNull("The result should not be null", result);
        Assert.assertEquals("The result should match the expected ImageData instance", standardResolutionMock, result);
    }
}
