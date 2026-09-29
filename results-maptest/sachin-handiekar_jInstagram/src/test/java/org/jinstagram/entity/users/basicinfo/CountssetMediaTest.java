package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class CountssetMediaTest {
    private Counts counts;

    @Before
    public void setUp() {
        counts = new Counts();
    }

    @After
    public void tearDown() {
        counts = null;
    }

    @Test
    public void testSetMedia() throws Exception {
        int expectedMedia = 12345;
        counts.setMedia(expectedMedia);
        Assert.assertEquals("The media value should be set correctly", expectedMedia, counts.getMedia());
    }
}
