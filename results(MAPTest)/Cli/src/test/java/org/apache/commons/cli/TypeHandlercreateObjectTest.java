package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

public class TypeHandlercreateObjectTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCreateObject_ValidClassName_ReturnsInstance() throws Exception {
        // Arrange
        String className = "java.util.ArrayList";

        // Act
        Object result = TypeHandler.createObject(className);

        // Assert
        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof java.util.List);
    }

    @Test
    public void testCreateObject_InvalidClassName_ThrowsParseException() throws Exception {
        // Arrange
        String className = "com.example.NonExistentClass";

        // Act & Assert
        thrown.expect(ParseException.class);
        thrown.expectMessage("Unable to find the class: com.example.NonExistentClass");
        TypeHandler.createObject(className);
    }

    @Test
    public void testCreateObject_ClassWithoutPublicNoArgsConstructor_ThrowsParseException() throws Exception {
        // Arrange
        String className = "org.apache.commons.cli.TypeHandlercreateObjectTest$TestClassWithoutNoArgsConstructor";

        // Act & Assert
        thrown.expect(ParseException.class);
        thrown.expectMessage("Unable to create an instance of: org.apache.commons.cli.TypeHandlercreateObjectTest$TestClassWithoutNoArgsConstructor");
        TypeHandler.createObject(className);
    }

    private static class TestClassWithoutNoArgsConstructor {
        private TestClassWithoutNoArgsConstructor() {
            // private constructor
        }
    }
}
