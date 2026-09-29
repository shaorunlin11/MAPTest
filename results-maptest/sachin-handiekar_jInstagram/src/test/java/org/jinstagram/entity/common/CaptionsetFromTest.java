package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

public class CaptionsetFromTest {

    @Test
    public void testSetFrom() throws Exception {
        Caption caption = new Caption();
        FromTagData fromData = new FromTagData();

        caption.setFrom(fromData);

        // Use reflection to verify the 'from' field was set
        java.lang.reflect.Field fromField = Caption.class.getDeclaredField("from");
        fromField.setAccessible(true);
        Object result = fromField.get(caption);

        Assert.assertEquals(fromData, result);
    }
}
