package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class HelpFormatterfindWrapPosTest {
    @Test
    public void testFindWrapPos_NewlineWithinWidth() {
        HelpFormatter formatter = new HelpFormatter();
        String text = "hello\nworld";
        int width = 6;
        int startPos = 0;
        int result = formatter.findWrapPos(text, width, startPos);
        assertEquals(6, result);
    }

    @Test
    public void testFindWrapPos_TabWithinWidth() {
        HelpFormatter formatter = new HelpFormatter();
        String text = "hello\tworld";
        int width = 6;
        int startPos = 0;
        int result = formatter.findWrapPos(text, width, startPos);
        assertEquals(6, result);
    }

    @Test
    public void testFindWrapPos_TextLengthExceedsWidth() {
        HelpFormatter formatter = new HelpFormatter();
        String text = "hello world";
        int width = 5;
        int startPos = 0;
        int result = formatter.findWrapPos(text, width, startPos);
        assertEquals(5, result);
    }

    @Test
    public void testFindWrapPos_WhitespaceFound() {
        HelpFormatter formatter = new HelpFormatter();
        String text = "hello world";
        int width = 6;
        int startPos = 0;
        int result = formatter.findWrapPos(text, width, startPos);
        assertEquals(5, result);
    }

    @Test
    public void testFindWrapPos_NoWhitespaceFound() {
        HelpFormatter formatter = new HelpFormatter();
        String text = "helloworld";
        int width = 5;
        int startPos = 0;
        int result = formatter.findWrapPos(text, width, startPos);
        assertEquals(5, result);
    }

    @Test
    public void testFindWrapPos_EndOfText() {
        HelpFormatter formatter = new HelpFormatter();
        String text = "hello";
        int width = 5;
        int startPos = 0;
        int result = formatter.findWrapPos(text, width, startPos);
        assertEquals(-1, result);
    }

    @Test
    public void testFindWrapPos_StartPositionBeyondTextLength() {
        HelpFormatter formatter = new HelpFormatter();
        String text = "hello";
        int width = 5;
        int startPos = 6;
        int result = formatter.findWrapPos(text, width, startPos);
        assertEquals(-1, result);
    }

    @Test
    public void testFindWrapPos_MultipleNewlines() {
        HelpFormatter formatter = new HelpFormatter();
        String text = "hello\nworld\nagain";
        int width = 6;
        int startPos = 0;
        int result = formatter.findWrapPos(text, width, startPos);
        assertEquals(6, result);
    }

    @Test
    public void testFindWrapPos_MultipleTabs() {
        HelpFormatter formatter = new HelpFormatter();
        String text = "hello\tworld\tagain";
        int width = 6;
        int startPos = 0;
        int result = formatter.findWrapPos(text, width, startPos);
        assertEquals(6, result);
    }

    @Test
    public void testFindWrapPos_WhitespaceAtEndOfWidth() {
        HelpFormatter formatter = new HelpFormatter();
        String text = "hello world";
        int width = 11;
        int startPos = 0;
        int result = formatter.findWrapPos(text, width, startPos);
        assertEquals(-1, result);
    }
}
