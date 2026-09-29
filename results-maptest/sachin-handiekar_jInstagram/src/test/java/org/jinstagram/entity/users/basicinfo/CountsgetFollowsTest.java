package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class CountsgetFollowsTest {

    @Test
    public void testGetFollows() throws Exception {
        Counts counts = new Counts();
        Field followsField = Counts.class.getDeclaredField("follows");
        followsField.setAccessible(true);
        followsField.setInt(counts, 42);
        assertEquals(42, counts.getFollows());
    }
}
