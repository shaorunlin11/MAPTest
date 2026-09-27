package org.jinstagram.utils;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Ignore;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

public class LogHelperlogExceptionTest {
    private Logger mockLogger;
    private String methodName = "testMethod";
    private Throwable exception = new RuntimeException("Test exception");
    private long timeSpent = 100;

    @Before
    public void setUp() {
        mockLogger = mock(Logger.class);
    }

    @After
    public void tearDown() {
        mockLogger = null;
    }

    @Test
    public void testLogException() throws Exception {
        LogHelper.logException(mockLogger, methodName, exception, timeSpent);

        String expectedMessage = String.format("Error in method {0}.\n\tException name: {1}.\n\t"
                + "Stack trace: {2}.\n\tTime spent in the method: {3} milliseconds.",
                methodName, exception.getClass().getSimpleName(), exception.getMessage(), timeSpent);
        verify(mockLogger).error(expectedMessage);
    }
}
