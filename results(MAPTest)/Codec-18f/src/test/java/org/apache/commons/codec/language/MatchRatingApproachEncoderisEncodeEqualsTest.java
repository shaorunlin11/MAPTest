package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatchRatingApproachEncoderisEncodeEqualsTest {

    @Test
    public void testIsEncodeEquals_NullName1() {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        assertFalse(encoder.isEncodeEquals(null, "test"));
    }

    @Test
    public void testIsEncodeEquals_NullName2() {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        assertFalse(encoder.isEncodeEquals("test", null));
    }

    @Test
    public void testIsEncodeEquals_EmptyName1() {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        assertFalse(encoder.isEncodeEquals("", "test"));
    }

    @Test
    public void testIsEncodeEquals_EmptyName2() {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        assertFalse(encoder.isEncodeEquals("test", ""));
    }

    @Test
    public void testIsEncodeEquals_SpaceName1() {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        assertFalse(encoder.isEncodeEquals(" ", "test"));
    }

    @Test
    public void testIsEncodeEquals_SpaceName2() {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        assertFalse(encoder.isEncodeEquals("test", " "));
    }

    @Test
    public void testIsEncodeEquals_LengthOneName1() {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        assertFalse(encoder.isEncodeEquals("a", "test"));
    }

    @Test
    public void testIsEncodeEquals_LengthOneName2() {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        assertFalse(encoder.isEncodeEquals("test", "a"));
    }

    @Test
    public void testIsEncodeEquals_CaseInsensitiveEqual() {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        assertTrue(encoder.isEncodeEquals("Test", "test"));
    }

    @Test
    public void testIsEncodeEquals_LengthDifferenceExceedsThree() {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        assertTrue(encoder.isEncodeEquals("abcde", "ab"));
    }

    @Test
    public void testIsEncodeEquals_ExactMatchAfterProcessing() {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        assertTrue(encoder.isEncodeEquals("John Doe", "John Doe"));
    }

    @Test
    public void testIsEncodeEquals_MatchAfterTransformations() {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        assertTrue(encoder.isEncodeEquals("Johndoe", "Johndoe"));
    }

    @Test
    public void testIsEncodeEquals_NoMatchAfterTransformations() {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        assertTrue(encoder.isEncodeEquals("Johndoe", "Johndo"));
    }
}
