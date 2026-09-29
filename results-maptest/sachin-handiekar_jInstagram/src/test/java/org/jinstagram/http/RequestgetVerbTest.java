package org.jinstagram.http;

import org.junit.Test;
import static org.junit.Assert.*;

public class RequestgetVerbTest {
    @Test
    public void testGetVerb() throws Exception {
        Verbs expectedVerb = Verbs.GET;
        Request request = new Request(expectedVerb, "http://example.com");
        Verbs actualVerb = request.getVerb();
        assertEquals(expectedVerb, actualVerb);
    }
}
