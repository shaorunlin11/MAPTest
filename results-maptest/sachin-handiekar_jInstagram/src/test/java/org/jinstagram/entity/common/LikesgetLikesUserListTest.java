package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;

public class LikesgetLikesUserListTest {

    @Test
    public void testGetLikesUserList() throws Exception {
        Likes likes = new Likes();
        List<User> expectedUserList = new ArrayList();
        expectedUserList.add(new User());

        // Use reflection to set the private field
        java.lang.reflect.Field likesUserListField = Likes.class.getDeclaredField("likesUserList");
        likesUserListField.setAccessible(true);
        likesUserListField.set(likes, expectedUserList);

        List<User> result = likes.getLikesUserList();
        Assert.assertEquals(expectedUserList, result);
    }
}
