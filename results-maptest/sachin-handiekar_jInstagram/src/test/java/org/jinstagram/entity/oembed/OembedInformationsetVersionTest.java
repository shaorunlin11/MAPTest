package org.jinstagram.entity.oembed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class OembedInformationsetVersionTest {
    private OembedInformation oembedInformation;

    @Before
    public void setUp() {
        oembedInformation = new OembedInformation();
    }

    @After
    public void tearDown() {
        oembedInformation = null;
    }

    @Test
    public void testSetVersion() throws Exception {
        String expectedVersion = "1.0";
        oembedInformation.setVersion(expectedVersion);
        Assert.assertEquals(expectedVersion, oembedInformation.getVersion());
    }

    @Test
    public void testSetVersionWithNull() throws Exception {
        oembedInformation.setVersion(null);
        Assert.assertNull(oembedInformation.getVersion());
    }
}
