package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class AssertionsnotNullTest {

    @Test
    public void testNotNullWithNonNullParameter() throws Exception {
        // Arrange
        Object parameter = new Object();
        String parameterName = "testParam";

        // Act
        Method method = Assertions.class.getDeclaredMethod("notNull", Object.class, String.class);
        method.setAccessible(true);
        method.invoke(null, parameter, parameterName);

        // Assert
        // No exception is expected
    }

    @Test
    public void testNotNullWithNullParameter() throws Exception {
        // Arrange
        Object parameter = null;
        String parameterName = "testParam";
        IllegalArgumentException exception = null;

        // Act
        try {
            Method method = Assertions.class.getDeclaredMethod("notNull", Object.class, String.class);
            method.setAccessible(true);
            method.invoke(null, parameter, parameterName);
        } catch (InvocationTargetException e) {
            exception = (IllegalArgumentException) e.getCause();
        }

        // Assert
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Parameter '" + parameterName + "' must not be null!"));
    }

    @Test
    public void testNotNullExceptionMessageContainsParameterName() throws Exception {
        // Arrange
        Object parameter = null;
        String parameterName = "testParam";
        IllegalArgumentException exception = null;

        // Act
        try {
            Method method = Assertions.class.getDeclaredMethod("notNull", Object.class, String.class);
            method.setAccessible(true);
            method.invoke(null, parameter, parameterName);
        } catch (InvocationTargetException e) {
            exception = (IllegalArgumentException) e.getCause();
        }

        // Assert
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Parameter '" + parameterName + "' must not be null!"));
    }
}
