package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class OembedInformationsetTypeTest {

    @Test
    public void testSetType() throws Exception {
        OembedInformation oembed = new OembedInformation();
        String expectedType = "video";

        oembed.setType(expectedType);

        Field typeField = OembedInformation.class.getDeclaredField("type");
        typeField.setAccessible(true);
        String actualType = (String) typeField.get(oembed);

        assertEquals(expectedType, actualType);
    }
}
