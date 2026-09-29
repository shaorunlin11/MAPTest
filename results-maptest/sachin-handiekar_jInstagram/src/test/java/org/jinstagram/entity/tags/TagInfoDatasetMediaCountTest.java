package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import com.google.gson.annotations.SerializedName;

public class TagInfoDatasetMediaCountTest {
    private TagInfoData tagInfoData;
    private long testMediaCount = 123456789L;

    @Before
    public void setUp() {
        tagInfoData = new TagInfoData();
    }

    @After
    public void tearDown() {
        tagInfoData = null;
    }

    @Test
    public void testSetMediaCount() throws Exception {
        // Act
        tagInfoData.setMediaCount(testMediaCount);

        // Assert
        Assert.assertEquals("The mediaCount should be set correctly", testMediaCount, tagInfoData.getMediaCount());
    }
}
