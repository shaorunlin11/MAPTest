package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class TextChunkadd_8bcd19efTest {
    private TextChunk textChunk;
    private TextElement textElement;

    @Before
    public void setUp() {
        textChunk = new TextChunk(0, 0, 100, 100);
        textElement = new TextElement(0, 0, 0, 0, null, 0, "", 0);
        textElement.y = 10;
        textElement.x = 20;
        textElement.width = 30;
        textElement.height = 40;
    }

    @After
    public void tearDown() {
        textChunk = null;
        textElement = null;
    }

    @Test
    public void testAddTextElement() {
        // Act
        textChunk.add(textElement);

        // Assert
        Assert.assertTrue(textChunk.textElements.contains(textElement));
    }
}
