package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.ArrayList;

import java.lang.reflect.Field;


public class LikesgetCountTest {
    private Likes likes;

    @Before
    public void setUp() {
        likes = new Likes();
    }

    @After
    public void tearDown() {
        likes = null;
    }

    @Test
    public void testGetCount_ReturnsInitializedValue() throws Exception {
        // Arrange
        int expectedCount = 42;
        Field countField = Likes.class.getDeclaredField("count");
        countField.setAccessible(true);
        countField.set(likes, expectedCount);

        // Act
        int actualCount = likes.getCount();

        // Assert
        Assert.assertEquals(expectedCount, actualCount);
    }
}
