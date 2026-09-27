package com.fasterxml.jackson.core.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class UTF8WriterillegalSurrogateDescTest {

    @Test
    public void testIllegalSurrogateDesc_OverMaxCodePoint() {
        String result = UTF8Writer.illegalSurrogateDesc(0x110000);
        assertEquals("Illegal character point (0x110000) to output; max is 0x10FFFF as per RFC 4627", result);
    }

    @Test
    public void testIllegalSurrogateDesc_UnderMaxCodePoint() {
        String result = UTF8Writer.illegalSurrogateDesc(0x10FFFF);
        assertEquals("Unmatched second part of surrogate pair (0x10ffff)", result);
    }

    @Test
    public void testIllegalSurrogateDesc_FirstSurrogateRange() {
        String result = UTF8Writer.illegalSurrogateDesc(0xD800);
        assertEquals("Unmatched first part of surrogate pair (0xd800)", result);
    }

    @Test
    public void testIllegalSurrogateDesc_LastFirstSurrogateRange() {
        String result = UTF8Writer.illegalSurrogateDesc(0xDBFF);
        assertEquals("Unmatched first part of surrogate pair (0xdbff)", result);
    }

    @Test
    public void testIllegalSurrogateDesc_SecondSurrogateRange() {
        String result = UTF8Writer.illegalSurrogateDesc(0xDC00);
        assertEquals("Unmatched second part of surrogate pair (0xdc00)", result);
    }

    @Test
    public void testIllegalSurrogateDesc_LastSecondSurrogateRange() {
        String result = UTF8Writer.illegalSurrogateDesc(0xDFFF);
        assertEquals("Unmatched second part of surrogate pair (0xdfff)", result);
    }

    @Test
    public void testIllegalSurrogateDesc_NormalCodePoint() {
        String result = UTF8Writer.illegalSurrogateDesc(0x41);
        assertEquals("Illegal character point (0x41) to output", result);
    }
}
