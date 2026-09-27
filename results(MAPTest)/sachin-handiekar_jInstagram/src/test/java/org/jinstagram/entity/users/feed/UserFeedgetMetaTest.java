package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.Pagination;
import java.util.List;
import java.util.ArrayList;

public class UserFeedgetMetaTest {
    private UserFeed userFeed;

    @Before
    public void setUp() {
        userFeed = new UserFeed();
        Meta meta = new Meta();
        Pagination pagination = new Pagination();
        List<UserFeedData> userList = new ArrayList<UserFeedData>();

        // Set up the userFeed with sample data
        userFeed.setMeta(meta);
        userFeed.setPagination(pagination);
        userFeed.setUserList(userList);
    }

    @Test
    public void testGetMetaReturnsExpectedMetaObject() {
        Meta result = userFeed.getMeta();
        Assert.assertNotNull("getMeta should not return null", result);
        Assert.assertEquals("getMeta should return the same Meta object that was set", userFeed.getMeta(), result);
    }
}
