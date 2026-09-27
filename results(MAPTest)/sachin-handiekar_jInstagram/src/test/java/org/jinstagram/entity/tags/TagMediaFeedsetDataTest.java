package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.users.feed.MediaFeedData;
import java.util.List;
import java.util.ArrayList;

public class TagMediaFeedsetDataTest {
    private TagMediaFeed tagMediaFeed;
    private List<MediaFeedData> testData;

    @Before
    public void setUp() {
        tagMediaFeed = new TagMediaFeed();
        testData = new ArrayList<MediaFeedData>();
        testData.add(new MediaFeedData());
    }

    @After
    public void tearDown() {
        tagMediaFeed = null;
        testData = null;
    }

    @Test
    public void testSetData() {
        tagMediaFeed.setData(testData);
        Assert.assertEquals(testData, tagMediaFeed.getData());
    }
}
