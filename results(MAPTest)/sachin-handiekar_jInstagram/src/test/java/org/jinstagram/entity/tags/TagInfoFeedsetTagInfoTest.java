package org.jinstagram.entity.tags;

import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.tags.TagInfoData;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class TagInfoFeedsetTagInfoTest {
    private TagInfoFeed tagInfoFeed;
    private TagInfoData tagInfoData;

    @Before
    public void setUp() {
        tagInfoFeed = new TagInfoFeed();
        tagInfoData = new TagInfoData();
    }

    @After
    public void tearDown() {
        tagInfoFeed = null;
        tagInfoData = null;
    }

    @Test
    public void testSetTagInfoWithValidData() {
        tagInfoFeed.setTagInfo(tagInfoData);
        assertEquals(tagInfoData, tagInfoFeed.getTagInfo());
    }
}
