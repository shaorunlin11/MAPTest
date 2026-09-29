package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class VideoDatagetWidthTest {
    private VideoData videoData;

    @Before
    public void setUp() {
        videoData = new VideoData();
    }

    @Test
    public void testGetWidth_ReturnsInitializedValue() throws Exception {
        // Arrange
        int expectedWidth = 1920;

        // Use reflection to set private field
        Field widthField = VideoData.class.getDeclaredField("width");
        widthField.setAccessible(true);
        widthField.setInt(videoData, expectedWidth);

        // Act
        int actualWidth = videoData.getWidth();

        // Assert
        assertEquals(expectedWidth, actualWidth);
    }

    @Test
    public void testGetWidth_DefaultValue() {
        // Arrange
        int expectedWidth = 0;

        // Act
        int actualWidth = videoData.getWidth();

        // Assert
        assertEquals(expectedWidth, actualWidth);
    }
}
