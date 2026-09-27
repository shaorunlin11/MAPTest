package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class VideoDatasetUrlTest {
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
    public void testSetUrl() throws Exception {
        String expectedUrl = "https://example.com/video.mp4";
        videoData.setUrl(expectedUrl);

        // Use reflection to verify the private field
        java.lang.reflect.Field urlField = VideoData.class.getDeclaredField("url");
        urlField.setAccessible(true);
        String actualUrl = (String) urlField.get(videoData);

        Assert.assertEquals("The url should be set correctly", expectedUrl, actualUrl);
    }
}
