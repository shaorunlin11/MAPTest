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
import java.lang.reflect.Field;

public class MediaFeedgetDataTest {
    private MediaFeed mediaFeed;

    @Before
    public void setUp() {
        mediaFeed = new MediaFeed();
    }

    @After
    public void tearDown() {
        mediaFeed = null;
    }

    @Test
    public void testGetData_ReturnsDataField() throws Exception {
        List<MediaFeedData> expectedData = new ArrayList<MediaFeedData>();
        Field dataField = MediaFeed.class.getDeclaredField("data");
        dataField.setAccessible(true);
        dataField.set(mediaFeed, expectedData);

        List<MediaFeedData> result = mediaFeed.getData();

        Assert.assertEquals(expectedData, result);
    }

    @Test
    public void testGetData_ReturnsNullIfDataNotInitialized() throws Exception {
        Field dataField = MediaFeed.class.getDeclaredField("data");
        dataField.setAccessible(true);
        dataField.set(mediaFeed, null);

        List<MediaFeedData> result = mediaFeed.getData();

        Assert.assertNull(result);
    }
}
