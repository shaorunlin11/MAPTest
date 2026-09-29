package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class CaptionsetTextTest {
    @Test
    public void testSetText() throws Exception {
        Caption caption = new Caption();
        String expectedText = "This is a test caption.";

        caption.setText(expectedText);

        Field textField = Caption.class.getDeclaredField("text");
        textField.setAccessible(true);
        String actualText = (String) textField.get(caption);

        assertEquals(expectedText, actualText);
    }
}
