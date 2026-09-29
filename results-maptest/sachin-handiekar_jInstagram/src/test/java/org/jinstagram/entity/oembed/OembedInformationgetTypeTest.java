package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class OembedInformationgetTypeTest {
    @Test
    public void testGetType() throws Exception {
        OembedInformation oembedInfo = new OembedInformation();
        String expectedType = "video";

        // Use reflection to set private field
        Field typeField = OembedInformation.class.getDeclaredField("type");
        typeField.setAccessible(true);
        typeField.set(oembedInfo, expectedType);

        String actualType = oembedInfo.getType();
        assertEquals(expectedType, actualType);
    }
}
