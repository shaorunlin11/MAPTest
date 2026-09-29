package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class VideostoStringTest {

    @Test
    public void testToString() throws Exception {
        Videos videos = new Videos();
        VideoData lowResolution = new VideoData();
        VideoData standardResolution = new VideoData();

        // Set the fields using reflection to bypass access modifiers
        Field lowResolutionField = Videos.class.getDeclaredField("lowResolution");
        lowResolutionField.setAccessible(true);
        lowResolutionField.set(videos, lowResolution);

        Field standardResolutionField = Videos.class.getDeclaredField("standardResolution");
        standardResolutionField.setAccessible(true);
        standardResolutionField.set(videos, standardResolution);

        String result = videos.toString();
        assertTrue(result.contains("lowResolution="));
        assertTrue(result.contains("standardResolution="));
    }
}
