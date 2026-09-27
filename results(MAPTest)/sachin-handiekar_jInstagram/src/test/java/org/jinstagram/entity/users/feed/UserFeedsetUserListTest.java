package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.users.feed.UserFeedData;

public class UserFeedsetUserListTest {
    private UserFeed userFeed;

    @Before
    public void setUp() {
        userFeed = new UserFeed();
    }

    @Test
    public void testSetUserList() {
        List<UserFeedData> userList = new ArrayList<UserFeedData>();
        userList.add(new UserFeedData());

        userFeed.setUserList(userList);

        Assert.assertEquals(userList, userFeed.getUserList());
    }
}
