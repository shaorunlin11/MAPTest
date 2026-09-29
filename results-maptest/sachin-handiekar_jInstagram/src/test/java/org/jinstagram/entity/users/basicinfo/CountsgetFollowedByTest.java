package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.lang.reflect.Field;

public class CountsgetFollowedByTest {
    private Counts counts;

    @Before
    public void setUp() {
        counts = new Counts();
    }

    @After
    public void tearDown() {
        counts = null;
    }

    @Test
    public void testGetFollowedByReturnsInitializedValue() {
        int expected = 42;
        try {
            Field followedByField = Counts.class.getDeclaredField("followedBy");
            followedByField.setAccessible(true);
            followedByField.set(counts, expected);
        } catch (Exception e) {
            Assert.fail("Failed to set private field: " + e.getMessage());
        }
        Assert.assertEquals(expected, counts.getFollowedBy());
    }
}
