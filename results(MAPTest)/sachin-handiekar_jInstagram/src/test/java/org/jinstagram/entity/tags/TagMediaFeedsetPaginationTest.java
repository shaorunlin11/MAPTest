package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Assert;

import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.users.feed.MediaFeedData;

public class TagMediaFeedsetPaginationTest {

    @Test
    public void testSetPagination() throws Exception {
        TagMediaFeed tagMediaFeed = new TagMediaFeed();
        Pagination pagination = new Pagination();

        tagMediaFeed.setPagination(pagination);

        java.lang.reflect.Field field = TagMediaFeed.class.getDeclaredField("pagination");
        field.setAccessible(true);
        Object result = field.get(tagMediaFeed);

        Assert.assertEquals(pagination, result);
    }
}
