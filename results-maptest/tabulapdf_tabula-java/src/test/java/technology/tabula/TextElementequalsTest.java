package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class TextElementequalsTest {

    @Test
    public void testEqualsSameInstance() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, "test", 0);
        assertTrue(element.equals(element));
    }

    @Test
    public void testEqualsNull() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, "test", 0);
        assertFalse(element.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, "test", 0);
        Object other = new Object();
        assertFalse(element.equals(other));
    }

    @Test
    public void testEqualsSuperclassNotEqual() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, "test", 0);
        TextElement other = new TextElement(1, 0, 10, 10, null, 12, "test", 0);
        assertFalse(element.equals(other));
    }

    @Test
    public void testEqualsFontNull() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, "test", 0);
        TextElement other = new TextElement(0, 0, 10, 10, null, 12, "test", 0);
        assertTrue(element.equals(other));
    }

    @Test
    public void testEqualsFontNotNull() {
        PDFont font = PDType1Font.HELVETICA;
        TextElement element = new TextElement(0, 0, 10, 10, font, 12, "test", 0);
        TextElement other = new TextElement(0, 0, 10, 10, font, 12, "test", 0);
        assertTrue(element.equals(other));
    }

    @Test
    public void testEqualsFontDifferent() {
        PDFont font1 = PDType1Font.HELVETICA;
        PDFont font2 = PDType1Font.TIMES_ROMAN;
        TextElement element = new TextElement(0, 0, 10, 10, font1, 12, "test", 0);
        TextElement other = new TextElement(0, 0, 10, 10, font2, 12, "test", 0);
        assertFalse(element.equals(other));
    }

    @Test
    public void testEqualsTextNull() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, null, 0);
        TextElement other = new TextElement(0, 0, 10, 10, null, 12, null, 0);
        assertTrue(element.equals(other));
    }

    @Test
    public void testEqualsTextNotNull() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, "test", 0);
        TextElement other = new TextElement(0, 0, 10, 10, null, 12, "test", 0);
        assertTrue(element.equals(other));
    }

    @Test
    public void testEqualsTextDifferent() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, "test", 0);
        TextElement other = new TextElement(0, 0, 10, 10, null, 12, "different", 0);
        assertFalse(element.equals(other));
    }

    @Test
    public void testEqualsFontSize() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, "test", 0);
        TextElement other = new TextElement(0, 0, 10, 10, null, 12, "test", 0);
        assertTrue(element.equals(other));
    }

    @Test
    public void testEqualsFontSizeDifferent() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, "test", 0);
        TextElement other = new TextElement(0, 0, 10, 10, null, 13, "test", 0);
        assertFalse(element.equals(other));
    }

    @Test
    public void testEqualsWidthOfSpace() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, "test", 0.5f);
        TextElement other = new TextElement(0, 0, 10, 10, null, 12, "test", 0.5f);
        assertTrue(element.equals(other));
    }

    @Test
    public void testEqualsWidthOfSpaceDifferent() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, "test", 0.5f);
        TextElement other = new TextElement(0, 0, 10, 10, null, 12, "test", 0.6f);
        assertFalse(element.equals(other));
    }

    @Test
    public void testEqualsDir() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, "test", 0.5f, 1.0f);
        TextElement other = new TextElement(0, 0, 10, 10, null, 12, "test", 0.5f, 1.0f);
        assertTrue(element.equals(other));
    }

    @Test
    public void testEqualsDirDifferent() {
        TextElement element = new TextElement(0, 0, 10, 10, null, 12, "test", 0.5f, 1.0f);
        TextElement other = new TextElement(0, 0, 10, 10, null, 12, "test", 0.5f, 2.0f);
        assertFalse(element.equals(other));
    }
}
