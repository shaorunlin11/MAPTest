package org.jinstagram.utils;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class LogHelperlogEntranceTest {
    private Logger mockLogger;

    @Before
    public void setUp() {
        mockLogger = Mockito.mock(Logger.class);
    }

    @After
    public void tearDown() {
        mockLogger = null;
    }

    @Test
    public void testLogEntranceWithValidParameters() {
        String methodName = "testMethod";
        String methodArguments = "arg1, arg2";

        LogHelper.logEntrance(mockLogger, methodName, methodArguments);

        String expectedMessage = String.format("Entering method %s.\n\tMethod arguments: [%s].", methodName, methodArguments);
        verify(mockLogger, times(1)).debug(expectedMessage);
    }

    @Test
    public void testLogEntranceWithNullLogger() {
        String methodName = "testMethod";
        String methodArguments = "arg1, arg2";

        try {
            LogHelper.logEntrance(null, methodName, methodArguments);
        } catch (NullPointerException e) {
            // Expected exception
        }
    }

    @Test
    public void testLogEntranceWithNullMethodName() {
        String methodName = null;
        String methodArguments = "arg1, arg2";

        LogHelper.logEntrance(mockLogger, methodName, methodArguments);

        String expectedMessage = String.format("Entering method %s.\n\tMethod arguments: [%s].", methodName, methodArguments);
        verify(mockLogger, times(1)).debug(expectedMessage);
    }

    @Test
    public void testLogEntranceWithNullMethodArguments() {
        String methodName = "testMethod";
        String methodArguments = null;

        LogHelper.logEntrance(mockLogger, methodName, methodArguments);

        String expectedMessage = String.format("Entering method %s.\n\tMethod arguments: [%s].", methodName, methodArguments);
        verify(mockLogger, times(1)).debug(expectedMessage);
    }
}
