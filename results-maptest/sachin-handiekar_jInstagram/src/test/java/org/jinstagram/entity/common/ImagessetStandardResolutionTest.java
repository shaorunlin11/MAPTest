package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class ImagessetStandardResolutionTest {
    private Images images;
    private ImageData testData;

    @Before
    public void setUp() {
        images = new Images();
        testData = new ImageData();
    }

    @After
    public void tearDown() {
        images = null;
        testData = null;
    }

    @Test
    public void testSetStandardResolution() throws Exception {
        images.setStandardResolution(testData);
        Assert.assertEquals(testData, images.getStandardResolution());
    }
}
