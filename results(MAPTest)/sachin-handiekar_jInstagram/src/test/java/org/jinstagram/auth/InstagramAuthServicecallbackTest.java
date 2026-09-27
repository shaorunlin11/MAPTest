package org.jinstagram.auth;
import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.utils.Preconditions;
public class InstagramAuthServicecallbackTest {


    @Test(expected = IllegalArgumentException.class)
    public void testCallbackWithInvalidUrl() throws Exception {
        InstagramAuthService service = new InstagramAuthService();
        String invalidUrl = "invalid-url";
        service.callback(invalidUrl);
    }
}
