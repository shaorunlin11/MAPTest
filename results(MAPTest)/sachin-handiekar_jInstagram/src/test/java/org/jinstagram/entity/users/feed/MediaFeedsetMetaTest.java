package org.jinstagram.entity.users.feed;

import org.jinstagram.entity.common.Meta;
import org.junit.Test;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class MediaFeedsetMetaTest {

    @Test
    public void testSetMeta() throws Exception {
        MediaFeed mediaFeed = new MediaFeed();
        Meta meta = new Meta();

        mediaFeed.setMeta(meta);

        Field field = MediaFeed.class.getDeclaredField("meta");
        field.setAccessible(true);
        Meta result = (Meta) field.get(mediaFeed);

        assertEquals(meta, result);
    }
}
