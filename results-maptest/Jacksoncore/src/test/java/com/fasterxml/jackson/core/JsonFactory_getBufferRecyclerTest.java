package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.BufferRecyclers;

public class JsonFactory_getBufferRecyclerTest {
    @Test
    public void testGetBufferRecycler_WhenFeatureEnabled_ReturnsFromBufferRecyclers() {
        // Arrange
        JsonFactory factory = new JsonFactory();
        int featureFlag = JsonFactory.Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING.getMask();
        factory._factoryFeatures = featureFlag;

        // Act
        BufferRecycler recycler = factory._getBufferRecycler();

        // Assert
        Assert.assertNotNull(recycler);
        Assert.assertTrue(recycler instanceof BufferRecycler);
    }

    @Test
    public void testGetBufferRecycler_WhenFeatureDisabled_ReturnsNewBufferRecycler() {
        // Arrange
        JsonFactory factory = new JsonFactory();
        factory._factoryFeatures = 0;

        // Act
        BufferRecycler recycler = factory._getBufferRecycler();

        // Assert
        Assert.assertNotNull(recycler);
        Assert.assertTrue(recycler instanceof BufferRecycler);
    }
}
