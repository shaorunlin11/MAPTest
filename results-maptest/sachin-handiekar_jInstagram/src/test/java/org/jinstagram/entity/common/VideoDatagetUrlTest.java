package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class VideoDatagetUrlTest {
    @Test
    public void testGetUrlReturnsInitializedValue() throws Exception {
        VideoData videoData = new VideoData();
        String expectedUrl = "https://example.com/video.mp4";
        Field urlField = VideoData.class.getDeclaredField("url");
        urlField.setAccessible(true);
        urlField.set(videoData, expectedUrl);

        String actualUrl = videoData.getUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testGetUrlReturnsNullWhenNotInitialized() throws Exception {
        VideoData videoData = new VideoData();
        Field urlField = VideoData.class.getDeclaredField("url");
        urlField.setAccessible(true);
        urlField.set(videoData, null);

        String actualUrl = videoData.getUrl();
        assertNull(actualUrl);
    }
}
