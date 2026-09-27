package org.jinstagram.entity.users.feed;

import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.Pagination;
import org.junit.Test;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class MediaFeedsetPaginationTest {

    @Test
    public void testSetPagination() throws Exception {
        MediaFeed mediaFeed = new MediaFeed();
        Pagination expectedPagination = new Pagination();

        mediaFeed.setPagination(expectedPagination);

        Field paginationField = MediaFeed.class.getDeclaredField("pagination");
        paginationField.setAccessible(true);
        Pagination actualPagination = (Pagination) paginationField.get(mediaFeed);

        assertEquals(expectedPagination, actualPagination);
    }
}
