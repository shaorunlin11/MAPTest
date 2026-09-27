package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class OembedInformationgetProviderNameTest {

    @Test
    public void testGetProviderName() throws Exception {
        OembedInformation oembedInfo = new OembedInformation();
        assertNull("providerName should be null initially", oembedInfo.getProviderName());

        String expectedProviderName = "TestProvider";
        Field providerNameField = OembedInformation.class.getDeclaredField("providerName");
        providerNameField.setAccessible(true);
        providerNameField.set(oembedInfo, expectedProviderName);

        assertEquals("providerName should return the set value", expectedProviderName, oembedInfo.getProviderName());
    }
}
