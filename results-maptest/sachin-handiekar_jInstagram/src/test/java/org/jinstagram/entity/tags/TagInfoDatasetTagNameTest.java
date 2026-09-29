package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class TagInfoDatasetTagNameTest {
    private TagInfoData tagInfoData;

    @Before
    public void setUp() {
        tagInfoData = new TagInfoData();
    }

    @After
    public void tearDown() {
        tagInfoData = null;
    }

    @Test
    public void testSetTagNameWithNonNullValue() throws Exception {
        String expectedTagName = "testTag";
        tagInfoData.setTagName(expectedTagName);
        Assert.assertEquals(expectedTagName, tagInfoData.getTagName());
    }

    @Test
    public void testSetTagNameWithNullValue() throws Exception {
        tagInfoData.setTagName(null);
        Assert.assertNull(tagInfoData.getTagName());
    }
}
