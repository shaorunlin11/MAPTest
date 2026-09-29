package org.jinstagram.auth.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TokentoStringTest {

    @Test
    public void testToStringWithNonNullValues() {
        Token token = new Token("myToken", "mySecret");
        String result = token.toString();
        assertEquals("Token[myToken , mySecret]", result);
    }

    @Test
    public void testToStringWithNullToken() throws Exception {
        Token token = new Token(null, "mySecret");
        String result = token.toString();
        assertEquals("Token[null , mySecret]", result);
    }

    @Test
    public void testToStringWithNullSecret() throws Exception {
        Token token = new Token("myToken", null);
        String result = token.toString();
        assertEquals("Token[myToken , null]", result);
    }

    @Test
    public void testToStringWithNullTokenAndSecret() throws Exception {
        Token token = new Token(null, null);
        String result = token.toString();
        assertEquals("Token[null , null]", result);
    }
}
