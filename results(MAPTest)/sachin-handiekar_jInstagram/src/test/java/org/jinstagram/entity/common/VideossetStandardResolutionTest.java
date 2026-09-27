package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class VideossetStandardResolutionTest {
    private Videos videos;
    private VideoData mockVideoData;

    @Before
    public void setUp() {
        videos = new Videos();
        mockVideoData = new VideoData();
    }

    @Test
    public void testSetStandardResolution_setsCorrectValue() throws Exception {
        videos.setStandardResolution(mockVideoData);

        Field field = Videos.class.getDeclaredField("standardResolution");
        field.setAccessible(true);
        VideoData result = (VideoData) field.get(videos);

        assertSame(mockVideoData, result);
    }
}
