package org.jinstagram.utils;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class LogHelperprettyPrintJSONResponseTest {
    private Logger mockLogger;

    @Before
    public void setUp() {
        mockLogger = mock(Logger.class);
    }

    @After
    public void tearDown() {
        mockLogger = null;
    }

    @Test
    public void testPrettyPrintJSONResponseWithValidJSON() {
        String validJson = "{\"key\":\"value\"}";
        when(mockLogger.isDebugEnabled()).thenReturn(true);

        LogHelper.prettyPrintJSONResponse(mockLogger, validJson);

        verify(mockLogger).isDebugEnabled();
        verify(mockLogger).debug("Received JSON response from Instagram - " + "{\n  \"key\": \"value\"\n}");
    }

    @Test
    public void testPrettyPrintJSONResponseWithInvalidJSON() {
        String invalidJson = "invalid json";
        when(mockLogger.isDebugEnabled()).thenReturn(true);

        LogHelper.prettyPrintJSONResponse(mockLogger, invalidJson);

        verify(mockLogger).isDebugEnabled();
        verify(mockLogger).debug("Received JSON response from Instagram - invalid json");
    }

    @Test
    public void testPrettyPrintJSONResponseWithNullJsonString() {
        when(mockLogger.isDebugEnabled()).thenReturn(true);

        LogHelper.prettyPrintJSONResponse(mockLogger, null);

        verify(mockLogger).isDebugEnabled();
        verify(mockLogger).debug("Received JSON response from Instagram - null");
    }

    @Test
    public void testPrettyPrintJSONResponseWhenDebugIsDisabled() {
        when(mockLogger.isDebugEnabled()).thenReturn(false);

        String validJson = "{\"key\":\"value\"}";

        LogHelper.prettyPrintJSONResponse(mockLogger, validJson);

        verify(mockLogger).isDebugEnabled();
        verify(mockLogger, never()).debug(anyString());
    }
}
