package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class LikessetCountTest {
    private Likes likes;

    @Before
    public void setUp() {
        likes = new Likes();
    }

    @Test
    public void testSetCountSetsCountFieldCorrectly() throws Exception {
        int expectedCount = 42;
        likes.setCount(expectedCount);

        Field countField = Likes.class.getDeclaredField("count");
        countField.setAccessible(true);
        Integer actualCount = (Integer) countField.get(likes);

        assertEquals((long) expectedCount, (long) actualCount);
    }
}
