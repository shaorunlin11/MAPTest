package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class VideossetLowResolutionTest {
    private Videos videos;
    private VideoData testVideoData;

    @Before
    public void setUp() {
        videos = new Videos();
        testVideoData = new VideoData();
    }

    @Test
    public void testSetLowResolution() {
        videos.setLowResolution(testVideoData);
        Assert.assertEquals(testVideoData, videos.getLowResolution());
    }
}
