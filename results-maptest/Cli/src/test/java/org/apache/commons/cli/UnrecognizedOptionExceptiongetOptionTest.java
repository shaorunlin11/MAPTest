package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class UnrecognizedOptionExceptiongetOptionTest {

    @Test
    public void testGetOptionWithConstructor1() throws Exception {
        String message = "Unrecognized option";
        UnrecognizedOptionException exception = new UnrecognizedOptionException(message);
        assertNull(exception.getOption());
    }

    @Test
    public void testGetOptionWithConstructor2() throws Exception {
        String message = "Unrecognized option";
        String option = "invalid";
        UnrecognizedOptionException exception = new UnrecognizedOptionException(message, option);
        assertEquals(option, exception.getOption());
    }
}
