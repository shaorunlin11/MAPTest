package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class UtilsroundTest {
    @Test
    public void testRound() {
        assertEquals(1.2f, Utils.round(1.234, 1), 0.0f);
        assertEquals(1.23f, Utils.round(1.2345, 2), 0.0f);
        assertEquals(1.235f, Utils.round(1.23456, 3), 0.0f);
        assertEquals(1.0f, Utils.round(1.0, 0), 0.0f);
        assertEquals(0.0f, Utils.round(0.0, 0), 0.0f);
        assertEquals(-1.2f, Utils.round(-1.234, 1), 0.0f);
        assertEquals(-1.23f, Utils.round(-1.2345, 2), 0.0f);
        assertEquals(-1.235f, Utils.round(-1.23456, 3), 0.0f);
    }
}
