package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class TagInfoDatagetTagNameTest {
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
    public void testGetTagName_ReturnsTagNameValue() {
        String expectedTagName = "testTag";
        // Use reflection to set the private field
        try {
            java.lang.reflect.Field field = TagInfoData.class.getDeclaredField("tagName");
            field.setAccessible(true);
            field.set(tagInfoData, expectedTagName);
        } catch (Exception e) {
            Assert.fail("Failed to set private field: " + e.getMessage());
        }

        String actualTagName = tagInfoData.getTagName();
        Assert.assertEquals(expectedTagName, actualTagName);
    }
}
