package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;

public class CarouselMediasetTypeTest {
    private CarouselMedia carouselMedia;
    private Field typeField;

    @Before
    public void setUp() throws Exception {
        carouselMedia = new CarouselMedia();
        typeField = CarouselMedia.class.getDeclaredField("type");
        typeField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        typeField.setAccessible(false);
    }

    @Test
    public void testSetType() throws Exception {
        String expectedType = "photo";
        carouselMedia.setType(expectedType);
        String actualType = (String) typeField.get(carouselMedia);
        Assert.assertEquals(expectedType, actualType);
    }
}
