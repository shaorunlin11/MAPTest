package org.jinstagram.entity.likes;

import java.util.List;
import java.util.ArrayList;
import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.User;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class LikesFeedgetUserListTest {

    @Test
    public void testGetUserList() throws Exception {
        // Arrange
        LikesFeed likesFeed = new LikesFeed();
        List<User> expectedUserList = new ArrayList<User>();
        expectedUserList.add(new User());
        expectedUserList.add(new User());

        // Use reflection to set the private userList field
        Field userListField = LikesFeed.class.getDeclaredField("userList");
        userListField.setAccessible(true);
        userListField.set(likesFeed, expectedUserList);

        // Act
        List<User> result = likesFeed.getUserList();

        // Assert
        assertNotNull(result);
        assertEquals(expectedUserList.size(), result.size());
        assertSame(expectedUserList.get(0), result.get(0));
        assertSame(expectedUserList.get(1), result.get(1));
    }
}
