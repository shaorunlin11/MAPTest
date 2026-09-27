package org.jinstagram.auth.oauth;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class InstagramServicegetVersionTest {
    @Test
    public void testGetVersionReturnsCorrectVersion() {
        InstagramService service = new InstagramService(null, null);
        assertEquals("1.0", service.getVersion());
    }
}
