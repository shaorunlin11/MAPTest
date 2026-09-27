package org.jinstagram.entity.tags;

import org.junit.Test;
import static org.junit.Assert.*;

public class TagInfoDatatoStringTest {

    @Test
    public void testToStringWithValidData() {
        TagInfoData tagInfoData = new TagInfoData();
        tagInfoData.setMediaCount(12345);
        tagInfoData.setTagName("java");

        String result = tagInfoData.toString();
        assertEquals("TagInfoData [mediaCount=12345, tagName=java]", result);
    }

    @Test
    public void testToStringWithNullTagName() {
        TagInfoData tagInfoData = new TagInfoData();
        tagInfoData.setMediaCount(67890);
        tagInfoData.setTagName(null);

        String result = tagInfoData.toString();
        assertEquals("TagInfoData [mediaCount=67890, tagName=null]", result);
    }
}
