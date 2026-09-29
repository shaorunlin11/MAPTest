package org.jinstagram.utils;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
public class LogHelperlogExitTest {
    private Logger mockLogger;

    @Before
    public void setUp() {
        mockLogger = Mockito.mock(Logger.class);
    }

    @After
    public void tearDown() {
        mockLogger = null;
    }
}
