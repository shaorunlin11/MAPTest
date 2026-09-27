package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class CaptiongetTextTest {
    @Test
    public void testGetText() throws Exception {
        Caption caption = new Caption();
        String expectedText = "This is a test caption.";
        Field textField = Caption.class.getDeclaredField("text");
        textField.setAccessible(true);
        textField.set(caption, expectedText);

        String result = caption.getText();
        assertEquals(expectedText, result);
    }

    @Test
    public void testGetTextWithNull() throws Exception {
        Caption caption = new Caption();
        Field textField = Caption.class.getDeclaredField("text");
        textField.setAccessible(true);
        textField.set(caption, null);

        String result = caption.getText();
        assertNull(result);
    }
}
