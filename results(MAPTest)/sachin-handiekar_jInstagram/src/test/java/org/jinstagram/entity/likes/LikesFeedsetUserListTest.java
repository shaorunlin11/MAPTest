package org.jinstagram.entity.likes;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.User;

import java.lang.reflect.Field;


public class LikesFeedsetUserListTest {
    private LikesFeed likesFeed;
    private List<User> userList;

    @Before
    public void setUp() {
        likesFeed = new LikesFeed();
        userList = new ArrayList<User>();
    }

    @After
    public void tearDown() {
        likesFeed = null;
        userList = null;
    }

    @Test
    public void testSetUserListAssignsUserListToInstanceVariable() throws Exception {
        // Arrange
        List<User> expectedUserList = userList;

        // Act
        likesFeed.setUserList(expectedUserList);

        // Assert
        Field userListField = LikesFeed.class.getDeclaredField("userList");
        userListField.setAccessible(true);
        List<User> actualUserList = (List<User>) userListField.get(likesFeed);
        Assert.assertEquals(expectedUserList, actualUserList);
    }

    @Test
    public void testSetUserListDoesNotModifyInputList() throws Exception {
        // Arrange
        List<User> inputUserList = userList;
        int originalSize = inputUserList.size();

        // Act
        likesFeed.setUserList(inputUserList);

        // Assert
        Assert.assertEquals(originalSize, inputUserList.size());
    }

    @Test
    public void testSetUserListHandlesNullValue() throws Exception {
        // Arrange
        List<User> nullUserList = null;

        // Act
        likesFeed.setUserList(nullUserList);

        // Assert
        Field userListField = LikesFeed.class.getDeclaredField("userList");
        userListField.setAccessible(true);
        List<User> actualUserList = (List<User>) userListField.get(likesFeed);
        Assert.assertNull(actualUserList);
    }
}
