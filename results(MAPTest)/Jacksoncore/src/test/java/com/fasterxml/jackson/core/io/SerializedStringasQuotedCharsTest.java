package com.fasterxml.jackson.core.io;
import org.junit.Test;
import org.junit.Assert;
import java.io.Serializable;
public class SerializedStringasQuotedCharsTest {
    @Test
    public void testAsQuotedChars_CachesResult() throws Exception {
        // Arrange
        SerializedString serializedString = new SerializedString("test");

        // Act
        char[] firstCall = serializedString.asQuotedChars();
        char[] secondCall = serializedString.asQuotedChars();

        // Assert
        Assert.assertSame(firstCall, secondCall);
    }


    @Test
    public void testAsQuotedChars_UsesBufferRecyclers() throws Exception {
        // Arrange
        SerializedString serializedString = new SerializedString("test");

        // Act
        char[] result = serializedString.asQuotedChars();

        // Assert
        Assert.assertNotNull(result);
    }
}
