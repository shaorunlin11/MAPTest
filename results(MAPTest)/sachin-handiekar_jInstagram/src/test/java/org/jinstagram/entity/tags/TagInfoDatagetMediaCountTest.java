package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


@RunWith(JUnit4.class)
public class TagInfoDatagetMediaCountTest {
    private TagInfoData tagInfoData;

    @Before
    public void setUp() {
        tagInfoData = new TagInfoData();
    }

    @After
    public void tearDown() {
        tagInfoData = null;
    }

    @Test
    public void testGetMediaCount_ReturnsInitializedValue() throws Exception {
        // Arrange
        long expectedMediaCount = 12345L;
        Field mediaCountField = TagInfoData.class.getDeclaredField("mediaCount");
        mediaCountField.setAccessible(true);
        mediaCountField.set(tagInfoData, expectedMediaCount);

        // Act
        long actualMediaCount = tagInfoData.getMediaCount();

        // Assert
        assertEquals(expectedMediaCount, actualMediaCount);
    }
}
