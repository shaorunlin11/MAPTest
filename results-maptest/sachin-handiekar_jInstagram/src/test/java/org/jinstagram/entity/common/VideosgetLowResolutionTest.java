package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class VideosgetLowResolutionTest {
    private Videos videos;

    @Before
    public void setUp() {
        videos = new Videos();
    }

    @After
    public void tearDown() {
        videos = null;
    }

    @Test
    public void testGetLowResolutionReturnsNullWhenNotSet() {
        Assert.assertNull(videos.getLowResolution());
    }

    @Test
    public void testGetLowResolutionReturnsSetVideoData() throws Exception {
        VideoData expected = new VideoData();
        Field lowResolutionField = Videos.class.getDeclaredField("lowResolution");
        lowResolutionField.setAccessible(true);
        lowResolutionField.set(videos, expected);

        VideoData result = videos.getLowResolution();
        Assert.assertEquals(expected, result);
    }
}
