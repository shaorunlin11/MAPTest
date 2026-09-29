package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class CarouselMediagetTypeTest {

    @Test
    public void testGetType() throws Exception {
        CarouselMedia carouselMedia = new CarouselMedia();
        String expectedType = "photo";
        Field typeField = CarouselMedia.class.getDeclaredField("type");
        typeField.setAccessible(true);
        typeField.set(carouselMedia, expectedType);

        String actualType = carouselMedia.getType();
        assertEquals(expectedType, actualType);
    }
}
