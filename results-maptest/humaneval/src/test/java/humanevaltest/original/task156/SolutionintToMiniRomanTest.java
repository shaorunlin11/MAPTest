package humanevaltest.original.task156;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionintToMiniRomanTest {
    @Test
    public void testIntToMiniRoman() {
        Solution solution = new Solution();

        assertEquals("i", solution.intToMiniRoman(1));
        assertEquals("iv", solution.intToMiniRoman(4));
        assertEquals("v", solution.intToMiniRoman(5));
        assertEquals("ix", solution.intToMiniRoman(9));
        assertEquals("x", solution.intToMiniRoman(10));
        assertEquals("xl", solution.intToMiniRoman(40));
        assertEquals("l", solution.intToMiniRoman(50));
        assertEquals("xc", solution.intToMiniRoman(90));
        assertEquals("c", solution.intToMiniRoman(100));
        assertEquals("cd", solution.intToMiniRoman(400));
        assertEquals("d", solution.intToMiniRoman(500));
        assertEquals("cm", solution.intToMiniRoman(900));
        assertEquals("m", solution.intToMiniRoman(1000));

        assertEquals("iii", solution.intToMiniRoman(3));
        assertEquals("xiv", solution.intToMiniRoman(14));
        assertEquals("xv", solution.intToMiniRoman(15));
        assertEquals("xix", solution.intToMiniRoman(19));
        assertEquals("xx", solution.intToMiniRoman(20));
        assertEquals("xxx", solution.intToMiniRoman(30));
        assertEquals("xliv", solution.intToMiniRoman(44));
        assertEquals("xlv", solution.intToMiniRoman(45));
        assertEquals("xcix", solution.intToMiniRoman(99));
        assertEquals("ccl", solution.intToMiniRoman(250));
        assertEquals("ccc", solution.intToMiniRoman(300));
        assertEquals("cdxi", solution.intToMiniRoman(411));
        assertEquals("cdxii", solution.intToMiniRoman(412));
        assertEquals("cdxiii", solution.intToMiniRoman(413));
        assertEquals("cdxiv", solution.intToMiniRoman(414));
        assertEquals("cdxv", solution.intToMiniRoman(415));
        assertEquals("cdxvi", solution.intToMiniRoman(416));
        assertEquals("cdxvii", solution.intToMiniRoman(417));
        assertEquals("cdxviii", solution.intToMiniRoman(418));
        assertEquals("cdxix", solution.intToMiniRoman(419));
        assertEquals("cdxx", solution.intToMiniRoman(420));
    }
}
