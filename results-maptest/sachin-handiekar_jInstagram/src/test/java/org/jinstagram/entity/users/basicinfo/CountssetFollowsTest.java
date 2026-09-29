package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class CountssetFollowsTest {
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
    public void testSetFollows() throws Exception {
        int expectedFollows = 100;
        counts.setFollows(expectedFollows);

        // Use reflection to verify the field value
        java.lang.reflect.Field followsField = Counts.class.getDeclaredField("follows");
        followsField.setAccessible(true);
        int actualFollows = followsField.getInt(counts);

        Assert.assertEquals(expectedFollows, actualFollows);
    }
}
