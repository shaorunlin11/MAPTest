package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import org.jinstagram.entity.common.User;
import org.jinstagram.entity.common.Likes;

public class LikessetLikesUserListTest {
    private Likes likes;

    @Before
    public void setUp() {
        likes = new Likes();
    }

    @Test
    public void testSetLikesUserListWithNonNullList() {
        List<User> userList = new ArrayList();
        User user = new User();
        userList.add(user);

        likes.setLikesUserList(userList);

        try {
            java.lang.reflect.Field field = Likes.class.getDeclaredField("likesUserList");
            field.setAccessible(true);
            List<User> result = (List<User>) field.get(likes);
            Assert.assertEquals(userList, result);
        } catch (Exception e) {
            Assert.fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testSetLikesUserListWithNull() {
        likes.setLikesUserList(null);

        try {
            java.lang.reflect.Field field = Likes.class.getDeclaredField("likesUserList");
            field.setAccessible(true);
            List<User> result = (List<User>) field.get(likes);
            Assert.assertNull(result);
        } catch (Exception e) {
            Assert.fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testSetLikesUserListWithEmptyList() {
        List<User> userList = new ArrayList();

        likes.setLikesUserList(userList);

        try {
            java.lang.reflect.Field field = Likes.class.getDeclaredField("likesUserList");
            field.setAccessible(true);
            List<User> result = (List<User>) field.get(likes);
            Assert.assertEquals(userList, result);
        } catch (Exception e) {
            Assert.fail("Unexpected exception: " + e.getMessage());
        }
    }
}
