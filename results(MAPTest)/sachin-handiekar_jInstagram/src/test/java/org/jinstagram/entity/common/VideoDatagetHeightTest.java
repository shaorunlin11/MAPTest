package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Field;


public class VideoDatagetHeightTest {
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
    public void testGetHeight_ReturnsInitializedValue() throws Exception {
        int expectedHeight = 720;
        Field heightField = VideoData.class.getDeclaredField("height");
        heightField.setAccessible(true);
        heightField.set(videoData, expectedHeight);

        int result = videoData.getHeight();
        Assert.assertEquals(expectedHeight, result);
    }
}
