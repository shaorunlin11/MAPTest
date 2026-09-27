package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.apache.commons.csv.Token.Type.INVALID;

public class TokentoStringTest {
    @Test
    public void testToString() throws Exception {
        Token token = new Token();
        token.type = INVALID;
        token.content.append("test");
        String result = token.toString();
        assertEquals("INVALID [test]", result);
    }
}
