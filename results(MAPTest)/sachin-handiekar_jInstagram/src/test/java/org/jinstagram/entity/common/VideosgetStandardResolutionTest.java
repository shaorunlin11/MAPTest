package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class VideosgetStandardResolutionTest {
    private Videos videos;
    private VideoData mockStandardResolution;

    @Before
    public void setUp() {
        videos = new Videos();
        mockStandardResolution = new VideoData();
    }

    @After
    public void tearDown() {
        videos = null;
        mockStandardResolution = null;
    }

    @Test
    public void testGetStandardResolution_returnsInitializedValue() throws Exception {
        // Arrange
        Field field = Videos.class.getDeclaredField("standardResolution");
        field.setAccessible(true);
        field.set(videos, mockStandardResolution);

        // Act
        VideoData result = videos.getStandardResolution();

        // Assert
        Assert.assertEquals(mockStandardResolution, result);
    }

    @Test
    public void testGetStandardResolution_returnsNullWhenNotSet() throws Exception {
        // Arrange
        Field field = Videos.class.getDeclaredField("standardResolution");
        field.setAccessible(true);
        field.set(videos, null);

        // Act
        VideoData result = videos.getStandardResolution();

        // Assert
        Assert.assertNull(result);
    }
}
