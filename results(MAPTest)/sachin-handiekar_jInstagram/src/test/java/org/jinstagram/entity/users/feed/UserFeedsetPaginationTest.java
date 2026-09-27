package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.entity.common.Pagination;

public class UserFeedsetPaginationTest {

    @Test
    public void testSetPagination() throws Exception {
        UserFeed userFeed = new UserFeed();
        Pagination pagination = new Pagination();

        userFeed.setPagination(pagination);

        // Use reflection to verify the field was set
        java.lang.reflect.Field field = UserFeed.class.getDeclaredField("pagination");
        field.setAccessible(true);
        Object result = field.get(userFeed);

        Assert.assertEquals(pagination, result);
    }
}
