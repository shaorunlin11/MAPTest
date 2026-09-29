package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

public class OembedInformationsetProviderNameTest {

    @Test
    public void testSetProviderName() throws Exception {
        OembedInformation oembed = new OembedInformation();
        String expectedProviderName = "Test Provider";

        oembed.setProviderName(expectedProviderName);

        // Use reflection to verify the field was set correctly
        java.lang.reflect.Field field = OembedInformation.class.getDeclaredField("providerName");
        field.setAccessible(true);
        String actualProviderName = (String) field.get(oembed);

        assertEquals(expectedProviderName, actualProviderName);
    }
}
