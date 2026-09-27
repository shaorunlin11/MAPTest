package org.jinstagram.entity.tags;

import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

public class TagMediaFeedtoStringTest {

    @Test
    public void testToString() throws Exception {
        TagMediaFeed tagMediaFeed = new TagMediaFeed();
        List<MediaFeedData> data = new ArrayList<MediaFeedData>();
        Meta meta = new Meta();
        Pagination pagination = new Pagination();

        // Set fields using reflection to bypass private access
        java.lang.reflect.Field dataField = TagMediaFeed.class.getDeclaredField("data");
        dataField.setAccessible(true);
        dataField.set(tagMediaFeed, data);

        java.lang.reflect.Field metaField = TagMediaFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(tagMediaFeed, meta);

        java.lang.reflect.Field paginationField = TagMediaFeed.class.getDeclaredField("pagination");
        paginationField.setAccessible(true);
        paginationField.set(tagMediaFeed, pagination);

        String result = tagMediaFeed.toString();
        String expected = String.format("TagMediaFeed [data=%s, meta=%s, pagination=%s]", data, meta, pagination);
        org.junit.Assert.assertEquals(expected, result);
    }
}
