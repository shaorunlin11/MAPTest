package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import com.fasterxml.jackson.core.io.NumberInput;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.lang.reflect.Field;

public class TextBuffercontentsAsDecimalTest {
    private TextBuffer textBuffer;
    private BufferRecycler bufferRecycler;

    @Before
    public void setUp() throws Exception {
        bufferRecycler = new BufferRecycler();
        textBuffer = new TextBuffer(bufferRecycler);
    }

    @After
    public void tearDown() throws Exception {
        textBuffer = null;
        bufferRecycler = null;
    }

    @Test
    public void testContentsAsDecimal_usesResultArrayIfAvailable() throws Exception {
        char[] resultArray = {'1', '2', '3'};
        Field resultArrayField = TextBuffer.class.getDeclaredField("_resultArray");
        resultArrayField.setAccessible(true);
        resultArrayField.set(textBuffer, resultArray);

        BigDecimal result = textBuffer.contentsAsDecimal();
        Assert.assertEquals(new BigDecimal("123"), result);
    }

    @Test
    public void testContentsAsDecimal_usesInputBufferIfResultArrayIsNull() throws Exception {
        char[] inputBuffer = {'4', '5', '6'};
        Field inputBufferField = TextBuffer.class.getDeclaredField("_inputBuffer");
        inputBufferField.setAccessible(true);
        inputBufferField.set(textBuffer, inputBuffer);

        Field inputStartField = TextBuffer.class.getDeclaredField("_inputStart");
        inputStartField.setAccessible(true);
        inputStartField.set(textBuffer, 0);

        Field inputLenField = TextBuffer.class.getDeclaredField("_inputLen");
        inputLenField.setAccessible(true);
        inputLenField.set(textBuffer, 3);

        BigDecimal result = textBuffer.contentsAsDecimal();
        Assert.assertEquals(new BigDecimal("456"), result);
    }

    @Test
    public void testContentsAsDecimal_usesCurrentSegmentIfNoInputBuffer() throws Exception {
        char[] currentSegment = {'7', '8', '9'};
        Field currentSegmentField = TextBuffer.class.getDeclaredField("_currentSegment");
        currentSegmentField.setAccessible(true);
        currentSegmentField.set(textBuffer, currentSegment);

        Field segmentSizeField = TextBuffer.class.getDeclaredField("_segmentSize");
        segmentSizeField.setAccessible(true);
        segmentSizeField.set(textBuffer, 0);

        Field currentSizeField = TextBuffer.class.getDeclaredField("_currentSize");
        currentSizeField.setAccessible(true);
        currentSizeField.set(textBuffer, 3);

        BigDecimal result = textBuffer.contentsAsDecimal();
        Assert.assertEquals(new BigDecimal("789"), result);
    }

    @Test
    public void testContentsAsDecimal_usesContentsAsArrayIfAllOtherSourcesAreUnavailable() throws Exception {
        Field resultArrayField = TextBuffer.class.getDeclaredField("_resultArray");
        resultArrayField.setAccessible(true);
        resultArrayField.set(textBuffer, null);

        Field inputBufferField = TextBuffer.class.getDeclaredField("_inputBuffer");
        inputBufferField.setAccessible(true);
        inputBufferField.set(textBuffer, null);

        Field currentSegmentField = TextBuffer.class.getDeclaredField("_currentSegment");
        currentSegmentField.setAccessible(true);
        currentSegmentField.set(textBuffer, null);

        Field segmentSizeField = TextBuffer.class.getDeclaredField("_segmentSize");
        segmentSizeField.setAccessible(true);
        segmentSizeField.set(textBuffer, 0);

        Field currentSizeField = TextBuffer.class.getDeclaredField("_currentSize");
        currentSizeField.setAccessible(true);
        currentSizeField.set(textBuffer, 3);

        char[] array = {'0', '1', '2'};
        Field currentSegmentField2 = TextBuffer.class.getDeclaredField("_currentSegment");
        currentSegmentField2.setAccessible(true);
        currentSegmentField2.set(textBuffer, array);

        BigDecimal result = textBuffer.contentsAsDecimal();
        Assert.assertEquals(new BigDecimal("012"), result);
    }

    @Test(expected = NumberFormatException.class)
    public void testContentsAsDecimal_throwsNumberFormatExceptionWhenParsingFails() throws Exception {
        char[] resultArray = new char[]{'a', 'b', 'c'};
        Field resultArrayField = TextBuffer.class.getDeclaredField("_resultArray");
        resultArrayField.setAccessible(true);
        resultArrayField.set(textBuffer, resultArray);
        textBuffer.contentsAsDecimal();
    }

@Test
    public void testContentsAsDecimal_executesTargetLines435() throws Exception {
        // Set up conditions to reach target line 435
        // _resultArray == null
        // _inputStart < 0 || _inputBuffer == null
        // _segmentSize == 0
        // _currentSegment != null

        // Set _resultArray to null
        Field resultArrayField = TextBuffer.class.getDeclaredField("_resultArray");
        resultArrayField.setAccessible(true);
        resultArrayField.set(textBuffer, null);

        // Set _inputStart to -1 and _inputBuffer to null
        Field inputStartField = TextBuffer.class.getDeclaredField("_inputStart");
        inputStartField.setAccessible(true);
        inputStartField.set(textBuffer, -1);

        Field inputBufferField = TextBuffer.class.getDeclaredField("_inputBuffer");
        inputBufferField.setAccessible(true);
        inputBufferField.set(textBuffer, null);

        // Set _segmentSize to 0
        Field segmentSizeField = TextBuffer.class.getDeclaredField("_segmentSize");
        segmentSizeField.setAccessible(true);
        segmentSizeField.set(textBuffer, 0);

        // Set _currentSegment to a non-null value
        char[] currentSegment = {'1', '2', '3'};
        Field currentSegmentField = TextBuffer.class.getDeclaredField("_currentSegment");
        currentSegmentField.setAccessible(true);
        currentSegmentField.set(textBuffer, currentSegment);

        // Set _currentSize to a non-zero value
        Field currentSizeField = TextBuffer.class.getDeclaredField("_currentSize");
        currentSizeField.setAccessible(true);
        currentSizeField.set(textBuffer, 3);

        BigDecimal result = textBuffer.contentsAsDecimal();
        Assert.assertEquals(new BigDecimal("123"), result);
    }
}
