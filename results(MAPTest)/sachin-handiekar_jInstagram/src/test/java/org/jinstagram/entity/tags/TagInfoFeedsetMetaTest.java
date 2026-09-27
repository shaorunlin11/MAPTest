package org.jinstagram.entity.tags;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.tags.TagInfoFeed;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class TagInfoFeedsetMetaTest {

    @Test
    public void testSetMetaWithValidMeta() throws Exception {
        TagInfoFeed tagInfoFeed = new TagInfoFeed();
        Meta meta = new Meta();

        tagInfoFeed.setMeta(meta);

        Field metaField = TagInfoFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        assertEquals(meta, metaField.get(tagInfoFeed));
    }

    @Test
    public void testSetMetaWithNullMeta() throws Exception {
        TagInfoFeed tagInfoFeed = new TagInfoFeed();
        Meta meta = null;

        tagInfoFeed.setMeta(meta);

        Field metaField = TagInfoFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        assertNull(metaField.get(tagInfoFeed));
    }
}
