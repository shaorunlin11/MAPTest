package com.fasterxml.jackson.core.format;

import org.junit.Test;
import static org.junit.Assert.*;

public class DataFormatMatchergetMatchStrengthTest {
    @Test
    public void testGetMatchStrengthWhenNull() throws Exception {
        // Arrange
        DataFormatMatcher matcher = new DataFormatMatcher(
            null, new byte[0], 0, 0, null, null
        );

        // Act
        MatchStrength result = matcher.getMatchStrength();

        // Assert
        assertEquals(MatchStrength.INCONCLUSIVE, result);
    }

    @Test
    public void testGetMatchStrengthWhenNonNull() throws Exception {
        // Arrange
        MatchStrength strength = MatchStrength.FULL_MATCH;
        DataFormatMatcher matcher = new DataFormatMatcher(
            null, new byte[0], 0, 0, null, strength
        );

        // Act
        MatchStrength result = matcher.getMatchStrength();

        // Assert
        assertEquals(strength, result);
    }
}
