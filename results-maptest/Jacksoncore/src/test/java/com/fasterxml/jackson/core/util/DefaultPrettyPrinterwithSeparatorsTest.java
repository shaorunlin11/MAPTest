package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.util.Separators;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

public class DefaultPrettyPrinterwithSeparatorsTest {

    @Test
    public void testWithSeparators() throws Exception {
        // Arrange
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();

        // Act
        Separators separators = new Separators() {
            private static final long serialVersionUID = 1L;
            @Override
            public char getObjectFieldValueSeparator() {
                return ':';
            }
        };
        DefaultPrettyPrinter result = printer.withSeparators(separators);

        // Assert
        assertEquals(separators, printer._separators);
        assertEquals(" : ", printer._objectFieldValueSeparatorWithSpaces);
        assertSame(printer, result);
    }
}
