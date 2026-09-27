package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class VideoDatasetHeightTest {
    private VideoData videoData;

    @Before
    public void setUp() {
        videoData = new VideoData();
    }

    @After
    public void tearDown() {
        videoData = null;
    }

    @Test
    public void testSetHeight() {
        int expectedHeight = 1080;
        videoData.setHeight(expectedHeight);
        Assert.assertEquals("The height should be set correctly", expectedHeight, videoData.getHeight());
    }
}
