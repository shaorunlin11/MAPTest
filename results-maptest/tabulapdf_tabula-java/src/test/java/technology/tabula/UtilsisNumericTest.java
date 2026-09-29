package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class UtilsisNumericTest {

    @Test
    public void testIsNumeric_NullInput_ReturnsFalse() {
        assertFalse(Utils.isNumeric(null));
    }

    @Test
    public void testIsNumeric_EmptyInput_ReturnsFalse() {
        assertFalse(Utils.isNumeric(""));
    }

    @Test
    public void testIsNumeric_NumericString_ReturnsTrue() {
        assertTrue(Utils.isNumeric("12345"));
    }

    @Test
    public void testIsNumeric_AlphanumericString_ReturnsFalse() {
        assertFalse(Utils.isNumeric("abc123"));
    }

    @Test
    public void testIsNumeric_StringWithSpecialCharacters_ReturnsFalse() {
        assertFalse(Utils.isNumeric("123!@#"));
    }

    @Test
    public void testIsNumeric_MixedNumericAndLetters_ReturnsFalse() {
        assertFalse(Utils.isNumeric("123abc"));
    }

    @Test
    public void testIsNumeric_AllDigits_ReturnsTrue() {
        assertTrue(Utils.isNumeric("0123456789"));
    }

    @Test
    public void testIsNumeric_OnlyWhitespace_ReturnsFalse() {
        assertFalse(Utils.isNumeric("   "));
    }

    @Test
    public void testIsNumeric_LeadingWhitespace_ReturnsFalse() {
        assertFalse(Utils.isNumeric(" 123"));
    }

    @Test
    public void testIsNumeric_TrailingWhitespace_ReturnsFalse() {
        assertFalse(Utils.isNumeric("123 "));
    }
}
