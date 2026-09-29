package org.jinstagram.entity.tags;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class TagMediaFeedsetMetaTest {

    @Test
    public void testSetMeta() throws Exception {
        TagMediaFeed tagMediaFeed = new TagMediaFeed();
        Meta meta = new Meta();

        tagMediaFeed.setMeta(meta);

        Field metaField = TagMediaFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        Meta result = (Meta) metaField.get(tagMediaFeed);

        assertEquals(meta, result);
    }
}
