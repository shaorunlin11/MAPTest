package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;

public class LikestoStringTest {
    @Test
    public void testToString() throws Exception {
        Likes likes = new Likes();
        // Use reflection to set private fields
        java.lang.reflect.Field countField = Likes.class.getDeclaredField("count");
        countField.setAccessible(true);
        countField.setInt(likes, 5);

        java.lang.reflect.Field likesUserListField = Likes.class.getDeclaredField("likesUserList");
        likesUserListField.setAccessible(true);
        List<User> likesUserList = new ArrayList<User>();
        likesUserListField.set(likes, likesUserList);

        String result = likes.toString();
        Assert.assertEquals("Likes [count=5, likesUserList=[]]", result);
    }
}
