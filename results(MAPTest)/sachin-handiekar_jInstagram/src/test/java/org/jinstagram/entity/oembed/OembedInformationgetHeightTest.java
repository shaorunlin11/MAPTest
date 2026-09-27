package org.jinstagram.entity.oembed;

import org.junit.Test;
import static org.junit.Assert.*;

public class OembedInformationgetHeightTest {

    @Test
    public void testGetHeight() throws Exception {
        OembedInformation oembed = new OembedInformation();
        oembed.setHeight("480");
        assertEquals("480", oembed.getHeight());
    }

    @Test
    public void testGetHeightWithNullValue() throws Exception {
        OembedInformation oembed = new OembedInformation();
        assertNull(oembed.getHeight());
    }
}
