package org.jinstagram.entity.oembed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.lang.reflect.Field;

public class OembedInformationgetAuthorNameTest {
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
    public void testGetAuthorName() throws Exception {
        String expectedAuthorName = "Test Author";
        Field authorNameField = OembedInformation.class.getDeclaredField("authorName");
        authorNameField.setAccessible(true);
        authorNameField.set(oembedInformation, expectedAuthorName);
        Assert.assertEquals(expectedAuthorName, oembedInformation.getAuthorName());
    }
}
