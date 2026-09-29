package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.Pagination;
import java.util.List;
import java.util.ArrayList;

public class UserFeedgetUserListTest {
    private UserFeed userFeed;
    private List<UserFeedData> userList;

    @Before
    public void setUp() {
        userFeed = new UserFeed();
        userList = new ArrayList<UserFeedData>();
        userList.add(new UserFeedData());
    }

    @After
    public void tearDown() {
        userFeed = null;
        userList = null;
    }

    @Test
    public void testGetUserListReturnsInitializedList() {
        // Arrange
        userFeed.setUserList(userList);

        // Act
        List<UserFeedData> result = userFeed.getUserList();

        // Assert
        Assert.assertNotNull("getUserList should not return null", result);
        Assert.assertEquals("getUserList should return the same list instance", userList, result);
    }

    @Test
    public void testGetUserListReturnsNullWhenNotInitialized() {
        // Arrange
        userFeed.setUserList(null);

        // Act
        List<UserFeedData> result = userFeed.getUserList();

        // Assert
        Assert.assertNull("getUserList should return null when userList is not initialized", result);
    }
}
