package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

public class OembedInformationsetAuthorNameTest {

    @Test
    public void testSetAuthorName() throws Exception {
        OembedInformation oembed = new OembedInformation();
        String expectedAuthorName = "Test Author";

        oembed.setAuthorName(expectedAuthorName);

        // Use reflection to verify the field was set
        java.lang.reflect.Field field = OembedInformation.class.getDeclaredField("authorName");
        field.setAccessible(true);
        String actualAuthorName = (String) field.get(oembed);

        assertEquals(expectedAuthorName, actualAuthorName);
    }
}
